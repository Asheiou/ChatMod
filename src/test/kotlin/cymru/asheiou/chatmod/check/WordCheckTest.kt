package cymru.asheiou.chatmod.check

import cymru.asheiou.chatmod.ChatMod
import cymru.asheiou.chatmod.EventCreator
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockbukkit.mockbukkit.MockBukkit
import org.mockbukkit.mockbukkit.ServerMock

class WordCheckTest {
  lateinit var server: ServerMock
  lateinit var plugin: ChatMod

  @BeforeEach
  fun setUp() {
    server = MockBukkit.mock()
    plugin = MockBukkit.load(ChatMod::class.java)
  }

  @AfterEach
  fun tearDown() {
    MockBukkit.unmock()
  }

  @Test
  fun `blocked word`() {
    val event = EventCreator.createAsyncChatEvent("faggot", server) // I'm gay I can say this
    assertTrue(WordCheck(event, "word").test())
  }

  @Test
  fun `blocked word word boundary`() {
    val event = EventCreator.createAsyncChatEvent("a fa ggot", server)
    assertTrue(WordCheck(event, "word").test())
  }

  @Test
  fun `allowed message`() {
    val event = EventCreator.createAsyncChatEvent("allowed message", server)
    assertFalse(WordCheck(event, "word").test())
  }
}