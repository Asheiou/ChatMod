package cymru.asheiou.chatmod.command.chatmod

import cymru.asheiou.chatmod.ChatMod
import cymru.asheiou.chatmod.command.chatmod.handler.ReloadHandler
import cymru.asheiou.chatmod.command.chatmod.handler.SubCommandHandler
import cymru.asheiou.chatmod.sender.MessageSender
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

class ChatModCommandExecutor(val cm: ChatMod) : CommandExecutor {
  val subCommands: List<SubCommandHandler> = listOf(
    ReloadHandler(cm),
  )

  override fun onCommand(
    sender: CommandSender,
    command: Command,
    label: String,
    args: Array<out String>
  ): Boolean {
    if(args.isEmpty()){
      @Suppress("DEPRECATION")
      MessageSender.sendMessage(sender, "ChatMod v${cm.description.version} enabled.")
      return true
    }
    subCommands.forEach {
      if(args[0] in it.alias)
        return it.handle(sender, command, args)
    }
    MessageSender.sendMessage(sender, "Unrecognised usage! Usage:")
    return false
  }
}