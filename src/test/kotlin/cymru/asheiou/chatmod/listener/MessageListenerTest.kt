package cymru.asheiou.chatmod.listener

import cymru.asheiou.chatmod.ChatMod
import cymru.asheiou.chatmod.EventCreator
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockbukkit.mockbukkit.MockBukkit
import org.mockbukkit.mockbukkit.ServerMock
import org.mockbukkit.mockbukkit.entity.PlayerMock

class MessageListenerTest {
  lateinit var server: ServerMock
  lateinit var plugin: ChatMod
  lateinit var player: PlayerMock
  lateinit var listener: MessageListener

  @BeforeEach
  fun setUp() {
    server = MockBukkit.mock()
    plugin = MockBukkit.load(ChatMod::class.java)
    player = server.addPlayer()
    listener = MessageListener(plugin)
    plugin.config.set("caps-minimum-length", 5)
    plugin.config.set("threshold", 50)
    plugin.config.set("tests.letter-spam-threshold", 6)
    plugin.config.set("tests.spam-cooldown", 3)
  }

  @AfterEach
  fun tearDown() {
    MockBukkit.unmock()
  }

  @Test
  fun `passing message`() {
    try {
      val event = EventCreator.createAsyncChatEvent("ok", server, player)
      listener.onAsyncChatEvent(event)
      assertFalse(event.isCancelled)
    } catch (t: Throwable) {
      t.printStackTrace()
      fail(t)
    }
  }

  @Test
  fun `failing message`() {
    val event = EventCreator.createAsyncChatEvent("THIS MESSAGE IS NOT ok.", server, player)
    listener.onAsyncChatEvent(event)
    assertTrue(event.isCancelled)
  }
}