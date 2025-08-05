package cymru.asheiou.chatmod.check

import cymru.asheiou.chatmod.ChatMod
import cymru.asheiou.chatmod.EventCreator
import cymru.asheiou.chatmod.session.Session
import cymru.asheiou.chatmod.session.SessionManager
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockbukkit.mockbukkit.MockBukkit
import org.mockbukkit.mockbukkit.ServerMock
import org.mockbukkit.mockbukkit.entity.PlayerMock
import kotlin.test.Test

class SpamCheckTest {
  lateinit var server: ServerMock
  lateinit var plugin: ChatMod
  lateinit var player: PlayerMock
  lateinit var session: Session

  @BeforeEach
  fun setUp() {
    server = MockBukkit.mock()
    plugin = MockBukkit.load(ChatMod::class.java)
    player = server.addPlayer()
    SessionManager.create(player.uniqueId)
    session = SessionManager.get(player.uniqueId)
  }

  @AfterEach
  fun tearDown() {
    MockBukkit.unmock()
  }

  @Test
  fun `not spam`() {
    server.config.set("tests.spam-cooldown", 3)
    val event = EventCreator.createAsyncChatEvent("test", server, player)
    assertFalse(SpamCheck(plugin, event, "spam").test())
    assertEquals(0, session.spamCount)
  }

  @Test
  fun `spam test, first infraction`() {
    session.lastSuccessfulChatMessage = System.currentTimeMillis()
    val event = EventCreator.createAsyncChatEvent("test", server, player)
    assertFalse(SpamCheck(plugin, event, "spam").test())
    assertEquals(1, session.spamCount)
  }

  @Test
  fun `spam test, final infraction`() {
    session.lastSuccessfulChatMessage = System.currentTimeMillis()
    session.spamCount = 2
    val event = EventCreator.createAsyncChatEvent("test", server, player)
    assertTrue(SpamCheck(plugin, event, "spam").test())
    assertEquals(3, session.spamCount)
  }
}