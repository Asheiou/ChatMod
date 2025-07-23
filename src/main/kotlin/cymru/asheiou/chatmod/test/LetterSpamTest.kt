package cymru.asheiou.chatmod.test

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.plugin.java.JavaPlugin
import java.text.Normalizer

class LetterSpamTest(val plugin: JavaPlugin, event: AsyncChatEvent, permission: String) : ChatTest(event, permission) {
  override fun test(): Boolean {
    if(hasPermission) return false
    var lastLetter: Char? = null
    var repetitions = 1
    val threshold = plugin.config.getInt("tests.letter-spam-threshold")
    PlainTextComponentSerializer.plainText().serialize(event.message()).forEach { // Not using messagePrepped as to catch space spam
      val normalized = Normalizer.normalize(it.toString(), Normalizer.Form.NFD)
        .replace("\\p{M}".toRegex(), "")
        .first()
      if (normalized == lastLetter) repetitions++
      if (repetitions > threshold) return true
      lastLetter = it
      repetitions = 1
    }
    return false
  }
}