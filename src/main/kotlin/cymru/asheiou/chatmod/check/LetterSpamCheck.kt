package cymru.asheiou.chatmod.check

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.plugin.java.JavaPlugin
import java.text.Normalizer

class LetterSpamCheck(val plugin: JavaPlugin, event: AsyncChatEvent, permission: String) : ChatCheck(event, permission) {
  override fun test(): Boolean {
    if(hasPermission) return false
    var lastLetter: Char? = null
    var repetitions = 1
    val threshold = plugin.config.getInt("tests.letter-spam-threshold")
    if (threshold <= 1) return false
    Normalizer.normalize(PlainTextComponentSerializer.plainText().serialize(event.message()),
      Normalizer.Form.NFD).replace("\\p{M}".toRegex(), "").lowercase().forEach {
        // Not using messagePrepped as to catch space spam
      if (it == lastLetter) {
        repetitions++
        if (repetitions >= threshold) {
          return true
        }
      } else {
        repetitions = 1
        lastLetter = it
      }
    }
    return false
  }
}