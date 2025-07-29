package cymru.asheiou.chatmod.placeholder

import cymru.asheiou.chatmod.exception.NotInitializedException
import org.bukkit.configuration.file.FileConfiguration

object NullFileConfiguration: FileConfiguration() {
  override fun saveToString(): String {
    throw NotInitializedException()
  }

  override fun loadFromString(contents: String) {
    throw NotInitializedException()
  }
}

/* Generic FileConfiguration to serve as a null alternative for BlockedWordsAccessor*/
