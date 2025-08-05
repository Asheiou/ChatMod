package cymru.asheiou.chatmod.listener

import cymru.asheiou.chatmod.check.CapsLockCheck
import cymru.asheiou.chatmod.check.LetterSpamCheck
import cymru.asheiou.chatmod.check.SpamCheck
import cymru.asheiou.chatmod.check.WordCheck
import cymru.asheiou.chatmod.sender.MessageSender
import cymru.asheiou.chatmod.session.SessionManager
import io.papermc.paper.event.player.AsyncChatEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class MessageListener(val plugin: JavaPlugin) : Listener {

  @EventHandler(priority = EventPriority.HIGH)
  fun onAsyncChatEvent(event: AsyncChatEvent) {
    if (event.isCancelled) return
    val checks = mapOf(
      CapsLockCheck(plugin, event, "caps") to "Too many caps!",
      SpamCheck(plugin, event, "spam") to "Too many messages in a short time! Please try again in a few seconds.",
      LetterSpamCheck(plugin, event, "letterspam") to "Too many repeated letters!",
      WordCheck(event, "word") to "Blocked word detected!"
    )
    for ((check, message) in checks) {
      if (check.test()) {
        plugin.logger.info("Cancelling message due to: ${check.javaClass.simpleName} failure")
        MessageSender.sendAndCancelEvent(event, event.player, message)
        return
      }
    }
    SessionManager.get(event.player.uniqueId).lastSuccessfulChatMessage = System.currentTimeMillis()
  }
}