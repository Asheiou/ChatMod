package cymru.asheiou.chatmod.accessor

import cymru.asheiou.chatmod.exception.NotInitializedException
import cymru.asheiou.chatmod.placeholder.NullFileConfiguration
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

  var config: FileConfiguration = NullFileConfiguration
    get() {
      if (field is NullFileConfiguration) reloadConfig()
      return field
    }

  var plugin: JavaPlugin? = null
    get() {
      return field ?: throw NotInitializedException()
    }

  fun reloadConfig() {
    val plugin = this.plugin!!
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
      config.setDefaults(defConfig)
    }
  }

  fun saveDefaultConfig() {
    val plugin = this.plugin!!
    if (configFile == null) {
      configFile = File(plugin.dataFolder, "blocked-words.yml")
    }
    if (!configFile!!.exists()) {
      plugin.saveResource("blocked-words.yml", false)
    }
  }
}