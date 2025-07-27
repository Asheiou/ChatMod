package cymru.asheiou.chatmod.listener

import cymru.asheiou.chatmod.sender.MessageSender
import cymru.asheiou.chatmod.test.*
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
      CapsLockTest(plugin, event, "caps") to "Too many caps!",
      SpamTest(plugin, event, "spam") to "Too many messages in a short time! Please try again in a few seconds.",
      LetterSpamTest(plugin, event, "letterspam") to "Too many repeated letters!",
      WordTest(event) to "Blocked word detected!"
    ).forEach {
      if (it.key.test()) MessageSender.sendAndCancelEvent(event, event.player, it.value)
    }
  }
}