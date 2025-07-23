package cymru.asheiou.chatmod.test

import io.papermc.paper.event.player.AsyncChatEvent

abstract class ChatTest(val event: AsyncChatEvent, val permission: String? = null) {
  abstract fun test(): Boolean

   val messagePrepped: String = run {
    event.message().toString().replace(" ", "")
  }

  val hasPermission: Boolean = run {
    permission ?: true
    event.player.hasPermission("chatmod.exempt.${permission}") || event.player.hasPermission("chatmod.exempt")
  }
}