package cymru.asheiou.chatmod.session

import java.util.*

object SessionManager {
  var activeSessions: MutableSet<Session> = mutableSetOf()

  fun create(uuid: UUID) = activeSessions.add(Session(uuid))

  fun get(uuid: UUID) = activeSessions.firstOrNull { it.uuid == uuid } ?: Session(uuid)

  fun remove(uuid: UUID): Boolean {
    return activeSessions.remove(get(uuid))
  }
}