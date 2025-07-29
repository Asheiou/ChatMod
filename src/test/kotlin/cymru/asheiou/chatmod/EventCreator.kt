package cymru.asheiou.chatmod

import io.papermc.paper.chat.ChatRenderer
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.chat.SignedMessage
import net.kyori.adventure.text.Component
import org.mockbukkit.mockbukkit.ServerMock
import org.mockbukkit.mockbukkit.entity.PlayerMock
import org.mockito.Mockito.mock

object EventCreator {
  fun createAsyncChatEvent(message: String, server: ServerMock, player: PlayerMock = server.addPlayer()): AsyncChatEvent {
    val testComponent = Component.text(message)
    val signedMessage = SignedMessage.system(message, testComponent)
    val audiences = setOf(mock(Audience::class.java))
    val renderer = mock(ChatRenderer::class.java)
    return AsyncChatEvent(
      true,
      player,
      audiences,
      renderer,
      testComponent,
      testComponent,
      signedMessage
    )
  }
}