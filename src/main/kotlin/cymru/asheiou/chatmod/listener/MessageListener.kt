package cymru.asheiou.chatmod.listener

import cymru.asheiou.chatmod.sender.MessageSender
import cymru.asheiou.chatmod.test.CapsLockTest
import cymru.asheiou.chatmod.test.LetterSpamTest
import cymru.asheiou.chatmod.test.SpamTest
import io.papermc.paper.event.player.AsyncChatEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class MessageListener(val plugin: JavaPlugin) : Listener {
  @EventHandler(priority = EventPriority.HIGH)
  fun onAsyncChatEvent(event: AsyncChatEvent) {
    if (event.isCancelled) return
    if (CapsLockTest(plugin, event, "caps").test())
      MessageSender.sendAndCancelEvent(event,event.player, "Too many caps!").also { return }
    if (SpamTest(plugin, event, "spam").test())
      MessageSender.sendAndCancelEvent(event,event.player,
        "Too many messages in a short time! Wait a few seconds and try again.").also { return }
    if (LetterSpamTest(plugin, event, "spam").test())
      MessageSender.sendAndCancelEvent(event,event.player, "Too many repeated letters!").also { return }
  }
}