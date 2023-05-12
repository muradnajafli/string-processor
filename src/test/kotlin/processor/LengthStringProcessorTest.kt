package processor

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class LengthStringProcessorTest {

    private lateinit var lengthProcessor: LengthStringProcessor

    @Parameterized.Parameter
    lateinit var shortInput: ConditionData

    @Parameterized.Parameter(1)
    lateinit var mediumInput: ConditionData

    @Parameterized.Parameter(2)
    lateinit var longInput: ConditionData

    @Before
    fun setup() {
        lengthProcessor = LengthStringProcessor()
    }

    @Test
    fun `when input satisfies SHORT condition should return short + input`() {
        for (shortString in shortInput.strings) {
            val result = lengthProcessor.process(shortString)
            assertEquals("short $shortString", result)
        }
    }

    @Test
    fun `when input satisfies MEDIUM condition should return medium + input`() {
        for (mediumString in mediumInput.strings) {
            val result = lengthProcessor.process(mediumString)
            assertEquals("medium $mediumString", result)
        }
    }

    @Test
    fun `when input satisfies LONG condition should return long + input`() {
        for (longString in longInput.strings) {
            val result = lengthProcessor.process(longString)
            assertEquals("long $longString", result)
        }
    }

    @Test
    fun `when string doesn't satisfy any condition should return as is`() {
        val tooLongString = "too long string check "
        val result = lengthProcessor.process(tooLongString)
        assertEquals(tooLongString, result)
    }


    /** Test data */
    class ConditionData private constructor(val strings: List<String>) {

        companion object {
            // length 0 .. 5
            fun short() = ConditionData(List(6) { index -> "S".repeat(index) })

            // length 6 .. 10
            fun medium() = ConditionData(List(5) { index -> "M".repeat(index + 6) })

            // length 11 .. 20
            fun long() = ConditionData(List(10) { index -> "L".repeat(index + 11) })
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters()
        fun data() = listOf(
            arrayOf(ConditionData.short(), ConditionData.medium(), ConditionData.long())
        )
    }
}