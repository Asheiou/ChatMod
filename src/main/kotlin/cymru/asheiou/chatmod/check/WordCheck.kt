package cymru.asheiou.chatmod.check

import cymru.asheiou.chatmod.accessor.BlockedWordsAccessor
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

class WordCheck(event: AsyncChatEvent, permission: String) : ChatCheck(event, permission) {
  var blockedWords = BlockedWordsAccessor.config.getStringList("words")
  override fun test(): Boolean {
    if(hasPermission) return false
    blockedWords.forEach {
      val regex = it.toRegex()
      if (regex.containsMatchIn(
          PlainTextComponentSerializer.plainText().serialize(event.message())))
        return true
    }
    return false
  }
}