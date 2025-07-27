package cymru.asheiou.chatmod.test

import cymru.asheiou.chatmod.accessor.BlockedWordsAccessor
import io.papermc.paper.event.player.AsyncChatEvent

class WordTest(event: AsyncChatEvent) : ChatTest(event) {
  val blockedWords = BlockedWordsAccessor.config!!.getStringList("words")
  override fun test(): Boolean {
    blockedWords.forEach {
      val regex = it.toRegex()
      if (regex.matches(messagePrepped)) return true
    }
    return false
  }
}