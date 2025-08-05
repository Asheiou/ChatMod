package cymru.asheiou.chatmod.check

import cymru.asheiou.chatmod.normalize
import io.papermc.paper.event.player.AsyncChatEvent
import org.bukkit.plugin.java.JavaPlugin

class LetterSpamCheck(val plugin: JavaPlugin, event: AsyncChatEvent, permission: String) :
  ChatCheck(event, permission) {
  override fun test(): Boolean {
    if (hasPermission) return false
    var lastLetter: Char? = null
    var repetitions = 1
    val threshold = plugin.config.getInt("tests.letter-spam-threshold")
    if (threshold <= 1) return false
    message.normalize().lowercase().forEach {
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