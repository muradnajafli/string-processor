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
    fun replace_string_no_occurrences() {
        val input = "some string"
        val result = replaceProcessor.process(input)
        assertEquals(input, result)
    }

    @Test
    fun replace_string_single_occurrences() {
        val input = "some string $STRING_TO_REPLACE"
        val result = replaceProcessor.process(input)
        assertEquals("some string $REPLACEMENT", result)
    }

    @Test
    fun replace_string_multiple_occurrences() {
        val input = "$STRING_TO_REPLACE some string $STRING_TO_REPLACE"
        val result = replaceProcessor.process(input)
        assertEquals("$REPLACEMENT some string $REPLACEMENT", result)
    }
}