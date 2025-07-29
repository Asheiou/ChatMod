package cymru.asheiou.chatmod.accessor

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
  var configInternal: FileConfiguration? = null

  var config: FileConfiguration?
    get() {
      if (configInternal == null) reloadConfig()
      return configInternal
    }
    set(value) {
      configInternal = value
    }

  lateinit var plugin: JavaPlugin

  fun reloadConfig() {
    if (configFile == null) {
      configFile = File(plugin.dataFolder, "blocked-words.yml")
    }
    config = YamlConfiguration.loadConfiguration(configFile!!)
    var defConfigStream: Reader? = null
    try {
      defConfigStream = InputStreamReader(Objects.requireNonNull<InputStream>(plugin.getResource("blocked-words.yml")))
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