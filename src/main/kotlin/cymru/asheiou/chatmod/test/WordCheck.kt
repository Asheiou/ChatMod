package cymru.asheiou.chatmod.test

import cymru.asheiou.chatmod.accessor.BlockedWordsAccessor
import io.papermc.paper.event.player.AsyncChatEvent

class WordCheck(event: AsyncChatEvent) : ChatCheck(event) {
  var blockedWords = BlockedWordsAccessor.config.getStringList("words")
  override fun test(): Boolean {
    blockedWords.forEach {
      val regex = it.toRegex()
      if (regex.containsMatchIn(messagePrepped)) return true
    }
    return false
  }
}