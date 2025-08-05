package cymru.asheiou.chatmod.check

import cymru.asheiou.chatmod.ChatMod
import cymru.asheiou.chatmod.EventCreator
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockbukkit.mockbukkit.MockBukkit
import org.mockbukkit.mockbukkit.ServerMock

class CapsLockCheckTest {
  lateinit var server: ServerMock
  lateinit var plugin: ChatMod

  @BeforeEach
  fun setUp() {
    server = MockBukkit.mock()
    plugin = MockBukkit.load(ChatMod::class.java)

    plugin.config.set("tests.caps-minimum-length", 5)
    plugin.config.set("tests.caps-threshold", 50)
  }

  @AfterEach
  fun tearDown() {
    MockBukkit.unmock()
  }

  @Test
  fun `test short message`() {
    val event = EventCreator.createAsyncChatEvent("AAA", server)
    assertFalse(CapsLockCheck(plugin, event, "caps").test())
  }

  @Test
  fun `test passing message`() {
    val event = EventCreator.createAsyncChatEvent("Testing case 1", server)
    assertFalse(CapsLockCheck(plugin, event, "caps").test())
  }

  @Test
  fun `test passing message 2`() {
    val event = EventCreator.createAsyncChatEvent("zero capital letters in this message", server)
    assertFalse(CapsLockCheck(plugin, event, "caps").test())
  }

  @Test
  fun `test failing message`() {
    val event = EventCreator.createAsyncChatEvent("TESTING CASE 2", server)
    assertTrue(CapsLockCheck(plugin, event, "caps").test())
  }
}