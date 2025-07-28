package cymru.asheiou.chatmod.command.chatmod.handler

import org.bukkit.command.Command
import org.bukkit.command.CommandSender

abstract class SubCommandHandler {
  abstract val alias: List<String>
  abstract fun handle(sender: CommandSender, command: Command, args: Array<out String>): Boolean
}