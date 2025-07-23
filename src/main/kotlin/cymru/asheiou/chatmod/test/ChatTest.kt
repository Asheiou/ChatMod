package cymru.asheiou.chatmod.test

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

abstract class ChatTest(val event: AsyncChatEvent, val permission: String? = null) {
  abstract fun test(): Boolean

  val messagePrepped: String = run {
    PlainTextComponentSerializer.plainText().serialize(event.message()).replace(" ", "")
  }

  val hasPermission: Boolean = run {
    permission ?: false
    event.player.hasPermission("chatmod.exempt.${permission}") || event.player.hasPermission("chatmod.exempt")
  }
}