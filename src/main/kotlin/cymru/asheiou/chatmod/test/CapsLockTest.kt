package cymru.asheiou.chatmod.test

import io.papermc.paper.event.player.AsyncChatEvent
import org.bukkit.plugin.java.JavaPlugin
import kotlin.math.ceil

class CapsLockTest(val plugin: JavaPlugin, event: AsyncChatEvent, permission: String) : ChatTest(event, permission) {
  override fun test() : Boolean {
    if (hasPermission) return false
    val thresholdDecimal = plugin.config.getInt("threshold") / 100.toDouble()
    val thresholdLength = ceil(message.length * thresholdDecimal)
    var capsFound = 0
    message.forEach {
      if (it.isUpperCase()) capsFound++
    }
    return capsFound >= thresholdLength
  }
}