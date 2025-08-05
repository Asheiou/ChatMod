package cymru.asheiou.chatmod.check

import cymru.asheiou.chatmod.accessor.BlockedWordsAccessor
import io.papermc.paper.event.player.AsyncChatEvent

class WordCheck(event: AsyncChatEvent, permission: String) : ChatCheck(event, permission) {
  var blockedWords = BlockedWordsAccessor.config.getStringList("words")
  override fun test(): Boolean {
    if(hasPermission) return false
    blockedWords.forEach {
      val regex = it.toRegex()
      if (regex.containsMatchIn(messagePrepped)) return true
    }
    return false
  }
}