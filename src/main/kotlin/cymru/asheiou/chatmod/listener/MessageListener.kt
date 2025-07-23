package cymru.asheiou.chatmod.listener

import cymru.asheiou.chatmod.sender.MessageSender
import cymru.asheiou.chatmod.test.CapsLockTest
import io.papermc.paper.event.player.AsyncChatEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class MessageListener(val plugin: JavaPlugin) : Listener {
  @EventHandler(priority = EventPriority.HIGH)
  fun onAsyncChatEvent(event: AsyncChatEvent) {
    if (event.isCancelled) return
    if (CapsLockTest(plugin, event, "caps").test()) {
      MessageSender.sendAndCancelEvent(event,event.player, "Too many caps!").also {return}
    }
  }
}