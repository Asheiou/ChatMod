package cymru.asheiou.chatmod.check

import io.papermc.paper.event.player.AsyncChatEvent
import org.bukkit.plugin.java.JavaPlugin
import kotlin.math.ceil

class CapsLockCheck(val plugin: JavaPlugin, event: AsyncChatEvent, permission: String) : ChatCheck(event, permission) {
  override fun test() : Boolean {
    if (hasPermission) return false
    if (messagePrepped.length < plugin.config.getInt("caps-minimum-length")) return false
    val thresholdDecimal = plugin.config.getInt("threshold") / 100.toDouble()
    val thresholdLength = ceil(messagePrepped.length * thresholdDecimal)
    var capsFound = 0
    messagePrepped.forEach {
      if (it.isUpperCase()) capsFound++
    }
    return capsFound >= thresholdLength
  }
}