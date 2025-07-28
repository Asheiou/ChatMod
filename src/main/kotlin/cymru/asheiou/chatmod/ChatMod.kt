package cymru.asheiou.chatmod

import cymru.asheiou.chatmod.accessor.BlockedWordsAccessor
import cymru.asheiou.chatmod.command.chatmod.ChatModCommandExecutor
import cymru.asheiou.chatmod.listener.MessageListener
import cymru.asheiou.chatmod.listener.SessionListener
import cymru.asheiou.configmanager.ConfigManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class ChatMod : JavaPlugin() {
  val configManager = ConfigManager(this, false)

  override fun onEnable() {
    logger.info("Load started.")
    BlockedWordsAccessor.saveDefaultConfig()
    val changes = configManager.loadConfig()
    logger.info(
      "Config loaded! " +
              if (changes[0] != -1) "${changes[0]} line(s) added, ${changes[1]} line(s) removed."
              else "File not found or unreadable. Reset it."
    )
    val pm = Bukkit.getPluginManager()
    pm.registerEvents(MessageListener(this), this)
    pm.registerEvents(SessionListener(), this)
    getCommand("chatmod")?.setExecutor(ChatModCommandExecutor(this))
    logger.info("Load complete!")
  }

  override fun onDisable() {
    logger.info("ttyl")
  }
}
