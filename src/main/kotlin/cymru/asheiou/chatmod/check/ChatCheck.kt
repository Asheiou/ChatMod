package cymru.asheiou.chatmod.check

import cymru.asheiou.chatmod.exception.NoPermissionException
import cymru.asheiou.chatmod.plaintext
import cymru.asheiou.chatmod.strip
import io.papermc.paper.event.player.AsyncChatEvent

abstract class ChatCheck(val event: AsyncChatEvent, val permission: String? = null) {
  abstract fun test(): Boolean

  val message: String by lazy {
    event.message().plaintext()
  }

  val messagePrepped: String by lazy {
    message.strip()
  }

  val hasPermission: Boolean by lazy {
    permission ?: throw NoPermissionException()
    event.player.hasPermission("chatmod.exempt.${permission}") || event.player.hasPermission("chatmod.exempt")
  }
}