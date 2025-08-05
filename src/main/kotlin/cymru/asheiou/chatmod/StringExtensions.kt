package cymru.asheiou.chatmod

import java.text.Normalizer

fun String.normalize(): String =
  Normalizer.normalize(this, Normalizer.Form.NFD).replace("\\p{M}".toRegex(), "")


fun String.strip(): String =
  this.normalize().replace(" ", "")
