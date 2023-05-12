package processor

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TrimStringProcessorTest {

    private lateinit var trimProcessor: TrimStringProcessor

    @Before
    fun setup() {
        trimProcessor = TrimStringProcessor()
    }

    @Test
    fun `spaces should be trimmed`() {
        val input = " trim "
        val result = trimProcessor.process(input)
        assertEquals("trim", result)
    }

    @Test
    fun `tabs should be trimmed`() {
        val input = "   trim    "
        val result = trimProcessor.process(input)
        assertEquals("trim", result)
    }

    @Test
    fun `multiple spaces should be trimmed`() {
        val input = "\ttrim\t"
        val result = trimProcessor.process(input)
        assertEquals("trim", result)
    }

    @Test
    fun `multiple tabs should be trimmed`() {
        val input = "\t\ttrim\t\t"
        val result = trimProcessor.process(input)
        assertEquals("trim", result)
    }
}