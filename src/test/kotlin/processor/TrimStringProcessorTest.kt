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
    fun check_trim_single_space() {
        val input = " trim "
        val result = trimProcessor.process(input)
        assertEquals("trim", result)
    }

    @Test
    fun check_trim_single_tab() {
        val input = "   trim    "
        val result = trimProcessor.process(input)
        assertEquals("trim", result)
    }

    @Test
    fun check_trim_multiple_space() {
        val input = "\ttrim\t"
        val result = trimProcessor.process(input)
        assertEquals("trim", result)
    }

    @Test
    fun check_trim_multiple_tab() {
        val input = "\t\ttrim\t\t"
        val result = trimProcessor.process(input)
        assertEquals("trim", result)
    }
}