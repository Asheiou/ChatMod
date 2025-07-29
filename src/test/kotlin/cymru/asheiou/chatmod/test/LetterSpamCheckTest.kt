package cymru.asheiou.chatmod.test

import cymru.asheiou.chatmod.ChatMod
import cymru.asheiou.chatmod.EventCreator
import jdk.jfr.Event
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockbukkit.mockbukkit.MockBukkit
import org.mockbukkit.mockbukkit.ServerMock

class LetterSpamCheckTest {
  lateinit var server: ServerMock
  lateinit var plugin: ChatMod

  @BeforeEach
  fun setUp() {
    server = MockBukkit.mock()
    plugin = MockBukkit.load(ChatMod::class.java)
    plugin.config.set("tests.letter-spam-threshold", 6)
  }

  @AfterEach
  fun tearDown() {
    MockBukkit.unmock()
  }

  @Test
  fun `test passing message`() {
    val event = EventCreator.createAsyncChatEvent("this doesnt contain spaaaam", server)
    assertFalse(LetterSpamCheck(plugin, event, "letterspam").test())
  }

  @Test
  fun `test failing message`() {
    val event = EventCreator.createAsyncChatEvent("AAAAAAAAAAAAAAA", server)
    assertTrue(LetterSpamCheck(plugin, event, "letterspam").test())
  }
}