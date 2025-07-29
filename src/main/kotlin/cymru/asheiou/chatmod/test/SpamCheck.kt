package cymru.asheiou.chatmod.test

import cymru.asheiou.chatmod.session.SessionManager
import io.papermc.paper.event.player.AsyncChatEvent
import org.bukkit.plugin.java.JavaPlugin

class SpamCheck(val plugin: JavaPlugin, event: AsyncChatEvent, permission: String) : ChatCheck(event, permission) {
  override fun test(): Boolean {
    if (hasPermission) return false
    val now = System.currentTimeMillis()
    val session = SessionManager.get(event.player.uniqueId)
    val spamCooldown = plugin.config.getLong("tests.spam-cooldown")
    if ((session.lastSuccessfulChatMessage - now) > spamCooldown) {
      session.spamCount = 0
      return false
    }
    session.spamCount++
    return session.spamCount >= 3
  }

}