package cymru.asheiou.chatmod.sender

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.event.Cancellable

object MessageSender {
  fun sendMessage(recipient: Audience, message: String, prefix: Boolean = true) {
    val compose = (if (prefix) "<yellow>[ChatMod]</yellow> " else "") + message
    recipient.sendMessage(MiniMessage.miniMessage().deserialize(compose))
  }

  fun sendAndCancelEvent(event: Cancellable, recipient: Audience, message: String, prefix: Boolean = true) {
    sendMessage(recipient, message, prefix)
    event.isCancelled = true
  }
}