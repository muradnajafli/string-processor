import io.mockk.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import processor.Processor

class StringProcessorTest {

    private lateinit var filter: StringProcessor

    @Before
    fun setup() {
        filter = StringProcessor()
    }

    @Test
    fun `when input is NULL and defaultString NOT CHANGED then empty string should be returned`() {
        val result = filter.process(null, emptyArray())
        assertEquals("", result)
    }

    @Test
    fun `when input is NULL processor should not be called`() {
        val processor = mockk<Processor>()
        filter.process(null, arrayOf(processor))
        verify {
            processor wasNot Called
        }
    }

    @Test
    fun `when input is NULL and defaultString IS CANGED then changed defaultString should be returned`() {
        val processor = mockk<Processor>()
        val defaultString = "some default"
        filter.defaultString = defaultString
        val result = filter.process(null, arrayOf(processor))
        assertEquals(defaultString, result)
    }

    @Test
    fun `when defaultString has CHANGED then isDefaultChanged flag should be true`() {
        filter.defaultString = "some string"
        assertTrue(filter.isDefaultChanged)
    }

    @Test
    fun `isDefaultChanged should be FALSE by default`() {
        assertFalse(filter.isDefaultChanged)
    }

    @Test
    fun `when processors array is empty then input should be returned as is`() {
        val input = "  Some string  "
        val result = filter.process(input, emptyArray())
        assertEquals(input, result)
    }

    @Test
    fun `when processor passed as parameter it should be applied`() {
        val input = "  Some string  "
        val expectedResult = "expected string"
        val processor = mockk<Processor>()
        every { processor.process(input) } returns expectedResult
        val result = filter.process(input, arrayOf(processor))
        assertEquals(expectedResult, result)
    }

    @Test
    fun `every single processor should be applied only once`() {
        val input = "  Some string  "
        val expectedResult = "expected string"
        val processor = mockk<Processor>()
        every { processor.process(input) } returns expectedResult
        filter.process(input, arrayOf(processor))
        verify(exactly = 1) {
            processor.process(input)
        }
    }

    @Test
    fun `when processors list has more then 1 item they should be applied in ASC order`() {
        val input = "  Some string  "
        val intermediateResult = "intermediate result"
        val expectedResult = "expected string"
        val firstProcessor = mockk<Processor>()
        val secondProcessor = mockk<Processor>()
        every { firstProcessor.process(input) } returns intermediateResult
        every { secondProcessor.process(intermediateResult) } returns expectedResult
        filter.process(input, arrayOf(firstProcessor, secondProcessor))
        verifySequence {
            firstProcessor.process(input)
            secondProcessor.process(intermediateResult)
        }
    }

    @Test
    fun `when processors list is EMPTY no modification should be applied`() {
        val firstInput = "input#1"
        val secondInput = "input#2"
        val inputs = arrayOf<String?>(firstInput, secondInput)
        filter.process(inputs, emptyArray())
        assertArrayEquals(arrayOf(firstInput, secondInput), inputs)
    }

    @Test
    fun `when processors list is EMPTY then returned count should be 0`() {
        val firstInput = "input#1"
        val secondInput = "input#2"
        val affectedStrings = filter.process(arrayOf(firstInput, secondInput), emptyArray())
        assertEquals(0, affectedStrings)
    }

    @Test
    fun `when only ONE string in input array was changed then processor should return 1`() {
        val firstInput = "input#1"
        val secondInput = "input#2"
        val secondInputResult = "output#2"

        val processor = mockk<Processor>()
        every { processor.process(firstInput) } returns firstInput
        every { processor.process(secondInput) } returns secondInputResult

        val inputs = arrayOf<String?>(firstInput, secondInput)
        val affectedStrings = filter.process(inputs, arrayOf(processor))
        assertEquals(1, affectedStrings)
    }

    @Test
    fun `when only ALL strings in input array was changed then processor should return INPUT SIZE`() {
        val firstInput = "input#1"
        val firstInputResult = "output#1"
        val secondInput = "input#2"
        val secondInputResult = "output#2"

        val processor = mockk<Processor>()
        every { processor.process(firstInput) } returns firstInputResult
        every { processor.process(secondInput) } returns secondInputResult

        val inputs = arrayOf<String?>(firstInput, secondInput)
        val affectedStrings = filter.process(inputs, arrayOf(processor))
        assertEquals(inputs.size, affectedStrings)
    }
}
