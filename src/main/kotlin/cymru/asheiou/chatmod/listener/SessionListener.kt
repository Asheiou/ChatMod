package cymru.asheiou.chatmod.listener

import cymru.asheiou.chatmod.session.SessionManager
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class SessionListener : Listener {
  @EventHandler(priority = EventPriority.LOW)
  fun onPlayerJoin(event: PlayerJoinEvent) {
    SessionManager.create(event.player.uniqueId)
  }

  @EventHandler(priority = EventPriority.LOW)
  fun onPlayerQuit(event: PlayerQuitEvent) {
    SessionManager.remove(event.player.uniqueId)
  }
}