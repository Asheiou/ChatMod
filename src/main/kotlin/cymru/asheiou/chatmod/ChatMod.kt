package cymru.asheiou.chatmod

import cymru.asheiou.configmanager.ConfigManager
import org.bukkit.plugin.java.JavaPlugin

class ChatMod : JavaPlugin() {
  lateinit var configManager: ConfigManager

  override fun onEnable() {
    logger.info("Load started.")
    configManager = ConfigManager(this, false)
    val changes = configManager.loadConfig()
    logger.info(
      "Config loaded! " +
              if (changes[0] != -1) "${changes[0]} line(s) added, ${changes[1]} line(s) removed."
              else "File not found or unreadable. Reset it."
    )
  }

  override fun onDisable() {
    logger.info("ttyl")
  }
}
