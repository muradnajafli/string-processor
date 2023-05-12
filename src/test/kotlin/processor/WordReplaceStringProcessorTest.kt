package processor

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WordReplaceStringProcessorTest {

    companion object {
        const val STRING_TO_REPLACE = "to replace"
        const val REPLACEMENT = "replacement"
    }

    private lateinit var replaceProcessor: WordReplaceStringProcessor

    @Before
    fun setup() {
        replaceProcessor = WordReplaceStringProcessor(STRING_TO_REPLACE, REPLACEMENT)
    }

    @Test
    fun `when input doesn't equal to substringToReplace it should be returned as is`() {
        val input = "some string"
        val result = replaceProcessor.process(input)
        assertEquals(input, result)
    }

    @Test
    fun `when input contains substringToReplace it should be replaced to replacement`() {
        val input = "some string $STRING_TO_REPLACE"
        val result = replaceProcessor.process(input)
        assertEquals("some string $REPLACEMENT", result)
    }

    @Test
    fun `when input contains severall substringToReplace all of them should be replaced to replacement`() {
        val input = "$STRING_TO_REPLACE some string $STRING_TO_REPLACE"
        val result = replaceProcessor.process(input)
        assertEquals("$REPLACEMENT some string $REPLACEMENT", result)
    }
}