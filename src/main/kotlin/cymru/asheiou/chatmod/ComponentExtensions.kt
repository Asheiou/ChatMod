package cymru.asheiou.chatmod

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

fun Component.plaintext(): String =
  PlainTextComponentSerializer.plainText().serialize(this)
