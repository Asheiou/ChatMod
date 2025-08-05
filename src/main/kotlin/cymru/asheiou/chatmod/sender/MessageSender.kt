package cymru.asheiou.chatmod.sender

import cymru.asheiou.chatmod.miniMessage
import net.kyori.adventure.audience.Audience
import org.bukkit.event.Cancellable

object MessageSender {
  fun sendMessage(recipient: Audience, message: String, prefix: Boolean = true) {
    val compose = (if (prefix) "<yellow>[ChatMod]</yellow> " else "") + message
    recipient.sendMessage(compose.miniMessage())
  }

  fun sendAndCancelEvent(event: Cancellable, recipient: Audience, message: String, prefix: Boolean = true) {
    sendMessage(recipient, message, prefix)
    event.isCancelled = true
  }
}