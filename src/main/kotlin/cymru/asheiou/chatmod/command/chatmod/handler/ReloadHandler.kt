package cymru.asheiou.chatmod.command.chatmod.handler

import cymru.asheiou.chatmod.ChatMod
import cymru.asheiou.chatmod.accessor.BlockedWordsAccessor
import cymru.asheiou.chatmod.sender.MessageSender
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class ReloadHandler(val cm: ChatMod) : SubCommandHandler() {
  override val alias = listOf("reload")
  override fun handle(
    sender: CommandSender,
    command: Command,
    args: Array<out String>
  ): Boolean {
    MessageSender.sendMessage(sender, "Starting reload...")
    val configReload = cm.configManager.loadConfig()
    if (configReload[0] == -1) {
      MessageSender.sendMessage(sender, "config.yml empty, missing, or unreadable! Generated a new one. Check console for more info.")
    }
    try {
      BlockedWordsAccessor.reloadConfig()
    } catch (e: Exception) {
      MessageSender.sendMessage(sender, "An error occurred reloading blocked_words.yml. Check console for more info.")
      cm.logger.severe(e.stackTraceToString())
      return true
    }
    MessageSender.sendMessage(sender, "<green>Reload complete.")
    return true
  }
}