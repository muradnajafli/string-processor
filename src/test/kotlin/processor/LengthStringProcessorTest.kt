package processor

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LengthStringProcessorTest {

    private lateinit var lengthProcessor: LengthStringProcessor

    @Before
    fun setup() {
        lengthProcessor = LengthStringProcessor()
    }

    @Test
    fun process_short_string() {
        val shortString = "test"
        val result = lengthProcessor.process(shortString)
        assertEquals("short $shortString", result)
    }

    @Test
    fun process_empty_string() {
        val emptyString = ""
        val result = lengthProcessor.process(emptyString)
        assertEquals("short ", result)
    }

    @Test
    fun process_max_short_string() {
        val shortString = "short"
        val result = lengthProcessor.process(shortString)
        assertEquals("short $shortString", result)
    }

    @Test
    fun process_min_medium_string() {
        val mediumString = "banana"
        val result = lengthProcessor.process(mediumString)
        assertEquals("medium $mediumString", result)
    }

    @Test
    fun process_medium_string() {
        val mediumString = "banana v2"
        val result = lengthProcessor.process(mediumString)
        assertEquals("medium $mediumString", result)
    }

    @Test
    fun process_max_medium_string() {
        val mediumString = "banana max"
        val result = lengthProcessor.process(mediumString)
        assertEquals("medium $mediumString", result)
    }

    @Test
    fun process_min_long_string() {
        val longString = "long string"
        val result = lengthProcessor.process(longString)
        assertEquals("long $longString", result)
    }

    @Test
    fun process_long_string() {
        val longString = "long string v2"
        val result = lengthProcessor.process(longString)
        assertEquals("long $longString", result)
    }

    @Test
    fun process_max_long_string() {
        val longString = "long string check v2"
        val result = lengthProcessor.process(longString)
        assertEquals("long $longString", result)
    }

    @Test
    fun process_string_no_changes() {
        val tooLongString = "too long string check "
        val result = lengthProcessor.process(tooLongString)
        assertEquals(tooLongString, result)
    }
}