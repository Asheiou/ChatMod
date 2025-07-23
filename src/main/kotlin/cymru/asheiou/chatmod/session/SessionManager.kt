package cymru.asheiou.chatmod.session

import java.util.UUID

class SessionManager {
  companion object {
    var activeSessions: MutableSet<Session> = mutableSetOf()

    fun create(uuid: UUID) = activeSessions.add(Session(uuid))

    fun get(uuid: UUID) = activeSessions.firstOrNull { it.uuid == uuid } ?: Session(uuid)

    fun remove(uuid: UUID) : Boolean {
      get(uuid)?.let {
        activeSessions.remove(it)
        return true
      }
      return false
    }
  }
}