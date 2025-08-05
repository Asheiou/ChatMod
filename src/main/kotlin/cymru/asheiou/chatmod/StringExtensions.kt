package cymru.asheiou.chatmod

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import java.text.Normalizer

fun String.normalize(): String =
  Normalizer.normalize(this, Normalizer.Form.NFD).replace("\\p{M}".toRegex(), "")


fun String.strip(): String =
  this.normalize().replace(" ", "")

fun String.miniMessage(): Component {
  val minimessage = MiniMessage.miniMessage()
  return minimessage.deserialize(this)
}