package cymru.asheiou.chatmod

import cymru.asheiou.chatmod.accessor.BlockedWordsAccessor
import cymru.asheiou.chatmod.accessor.ReadmeAccessor
import cymru.asheiou.chatmod.command.chatmod.ChatModCommandExecutor
import cymru.asheiou.chatmod.listener.MessageListener
import cymru.asheiou.chatmod.listener.SessionListener
import cymru.asheiou.configmanager.ConfigManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

open class ChatMod : JavaPlugin() {
  val configManager = ConfigManager(this, false)
  val readmeAccessor = ReadmeAccessor(this)

  override fun onEnable() {
    logger.info("Load started.")
    BlockedWordsAccessor.plugin = this
    BlockedWordsAccessor.saveDefaultConfig()
    if (readmeAccessor.init()) {
      logger.info("$AQUA*******Saved new readme.txt, please give it a read!*******$WHITE")
    }
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
    logger.info("${GREEN}Load complete!$WHITE")
  }

  override fun onDisable() {
    logger.info("ttyl")
  }

  companion object {
    const val AQUA = "\u001B36m"
    const val WHITE = "\u001B37m"
    const val GREEN = "\u001B32m"
  }
}
