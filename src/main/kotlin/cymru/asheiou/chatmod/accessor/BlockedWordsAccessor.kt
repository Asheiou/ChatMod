package cymru.asheiou.chatmod.accessor

import cymru.asheiou.chatmod.ChatMod
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.util.*

object BlockedWordsAccessor {
  var configFile: File? = null
  var config: FileConfiguration?
    get() {
      if (config == null) reloadConfig()
      return config
    }
    set(value) { config = value }
  var plugin: JavaPlugin = JavaPlugin.getProvidingPlugin(ChatMod::class.java)

  fun reloadConfig() {
    if (configFile == null) {
      configFile = File(plugin.dataFolder, "blocked-words.yml")
    }
    config = YamlConfiguration.loadConfiguration(configFile!!)
    var defConfigStream: Reader? = null
    try {
      defConfigStream = InputStreamReader(Objects.requireNonNull<InputStream>(plugin.getResource("servers.yml")))
    } catch (_: NullPointerException) {
      plugin.logger.warning("Could not load internal blocked-words.yml! Please check your build.")
    }
    if (defConfigStream != null) {
      val defConfig = YamlConfiguration.loadConfiguration(defConfigStream)
      config!!.setDefaults(defConfig)
    }
  }

  fun saveDefaultConfig() {
    if (configFile == null) {
      configFile = File(plugin.dataFolder, "blocked-words.yml")
    }
    if (!configFile!!.exists()) {
      plugin.saveResource("blocked-words.yml", false)
    }
  }
}