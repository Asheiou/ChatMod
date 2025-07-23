package cymru.asheiou.chatmod.session

import java.util.UUID

data class Session(
  val uuid: UUID,
  var lastSuccessfulChatMessage: Long = 0,
  var logInTime: Long = 0,
  var spamCount: Long = 0
)