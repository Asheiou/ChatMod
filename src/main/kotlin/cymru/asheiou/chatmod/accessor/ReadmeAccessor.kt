package cymru.asheiou.chatmod.accessor

import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class ReadmeAccessor(val plugin: JavaPlugin) {
  val readme: File = File(plugin.dataFolder, "readme.txt")

  fun init(): Boolean {
    if (!readme.exists() || !readme.isFile)
      return true.also { plugin.saveResource("readme.txt", true) }

    val readmeData = readme.readText()
    @Suppress("DEPRECATION")
    val version = plugin.description.version

    if (!readmeData.startsWith("ChatMod - Version $version"))
      return true.also { plugin.saveResource("readme.txt", true) }

    return false
  }
}