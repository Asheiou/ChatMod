package cymru.asheiou.chatmod.test

import cymru.asheiou.chatmod.ChatMod
import cymru.asheiou.chatmod.EventCreator
import cymru.asheiou.chatmod.accessor.BlockedWordsAccessor
import org.bukkit.block.Block
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
    assertTrue(WordCheck(event).test())
  }

  @Test
  fun `blocked word word boundary`() {
    val event = EventCreator.createAsyncChatEvent("a fa ggot", server)
    assertTrue(WordCheck(event).test())
  }

  @Test
  fun `allowed message`() {
    val event = EventCreator.createAsyncChatEvent("allowed message", server)
    assertFalse(WordCheck(event).test())
  }
}