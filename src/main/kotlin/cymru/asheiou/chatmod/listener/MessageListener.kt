package cymru.asheiou.chatmod.listener

import cymru.asheiou.chatmod.sender.MessageSender
import cymru.asheiou.chatmod.session.SessionManager
import cymru.asheiou.chatmod.check.*
import io.papermc.paper.event.player.AsyncChatEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class MessageListener(val plugin: JavaPlugin) : Listener {

  @EventHandler(priority = EventPriority.HIGH)
  fun onAsyncChatEvent(event: AsyncChatEvent) {
    if (event.isCancelled) return
    mapOf(
      CapsLockCheck(plugin, event, "caps") to "Too many caps!",
      SpamCheck(plugin, event, "spam") to "Too many messages in a short time! Please try again in a few seconds.",
      LetterSpamCheck(plugin, event, "letterspam") to "Too many repeated letters!",
      WordCheck(event, "word") to "Blocked word detected!"
    ).forEach {
      if (it.key.test()) {
        plugin.logger.info("Cancelling message due to: ${it.key.javaClass.simpleName} failure")
        MessageSender.sendAndCancelEvent(event, event.player, it.value)
      }
    }
    SessionManager.get(event.player.uniqueId).lastSuccessfulChatMessage = System.currentTimeMillis()
  }
}