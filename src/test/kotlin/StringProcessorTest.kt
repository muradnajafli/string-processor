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
    fun process_null_string_no_processor_default_string_not_changed_check_result() {
        val result = filter.process(null, emptyArray())
        assertEquals("", result)
    }

    @Test
    fun process_null_string_single_processor_default_string_not_changed_check_processor_not_invoked() {
        val processor = mockk<Processor>()
        filter.process(null, arrayOf(processor))
        verify {
            processor wasNot Called
        }
    }

    @Test
    fun process_null_string_single_processor_default_string_changed_check_result() {
        val processor = mockk<Processor>()
        val defaultString = "some default"
        filter.defaultString = defaultString
        val result = filter.process(null, arrayOf(processor))
        assertEquals(defaultString, result)
    }

    @Test
    fun change_default_value_check_flag() {
        filter.defaultString = "some string"
        assertTrue(filter.isDefaultChanged)
    }

    @Test
    fun check_default_flag() {
        assertFalse(filter.isDefaultChanged)
    }

    @Test
    fun filter_single_no_processors_to_apply() {
        val input = "  Some string  "
        val result = filter.process(input, emptyArray())
        assertEquals(input, result)
    }

    @Test
    fun filter_single_array_processors_to_apply() {
        val input = "  Some string  "
        val expectedResult = "expected string"
        val processor = mockk<Processor>()
        every { processor.process(input) } returns expectedResult
        val result = filter.process(input, arrayOf(processor))
        assertEquals(expectedResult, result)
    }

    @Test
    fun filter_single_array_processors_to_apply_check_processor_invocation_count() {
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
    fun filter_multiple_processors_to_apply() {
        val input = "  Some string  "
        val intermediateResult = "intermediate result"
        val expectedResult = "expected string"
        val firstProcessor = mockk<Processor>()
        val secondProcessor = mockk<Processor>()
        every { firstProcessor.process(input) } returns intermediateResult
        every { secondProcessor.process(intermediateResult) } returns expectedResult
        val result = filter.process(input, arrayOf(firstProcessor, secondProcessor))
        assertEquals(expectedResult, result)
    }

    @Test
    fun filter_multiple_processors_to_apply_check_invocation_order() {
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
    fun filter_array_of_strings_no_processors_check_array() {
        val firstInput = "input#1"
        val secondInput = "input#2"
        val inputs = arrayOf<String?>(firstInput, secondInput)
        filter.process(inputs, emptyArray())
        assertArrayEquals(arrayOf(firstInput, secondInput), inputs)
    }

    @Test
    fun filter_array_of_strings_no_processors_check_affected_strings() {
        val firstInput = "input#1"
        val secondInput = "input#2"
        val affectedStrings = filter.process(arrayOf(firstInput, secondInput), emptyArray())
        assertEquals(0, affectedStrings)
    }

    @Test
    fun filter_array_of_strings_single_processors_single_string_changed_check_array() {
        val firstInput = "input#1"
        val secondInput = "input#2"
        val secondInputResult = "output#2"
        val processor = mockk<Processor>()
        every { processor.process(firstInput) } returns firstInput
        every { processor.process(secondInput) } returns secondInputResult
        val inputs = arrayOf<String?>(firstInput, secondInput)
        filter.process(inputs, arrayOf(processor))
        assertArrayEquals(arrayOf(firstInput, secondInputResult), inputs)
    }

    @Test
    fun filter_array_of_strings_single_processors_single_string_changed_check_affected_strings() {
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
    fun filter_array_of_strings_single_processors_all_strings_changed_check_array() {
        val firstInput = "input#1"
        val firstInputResult = "output#1"
        val secondInput = "input#2"
        val secondInputResult = "output#2"
        val processor = mockk<Processor>()
        every { processor.process(firstInput) } returns firstInputResult
        every { processor.process(secondInput) } returns secondInputResult
        val inputs = arrayOf<String?>(firstInput, secondInput)
        filter.process(inputs, arrayOf(processor))
        assertArrayEquals(arrayOf(firstInputResult, secondInputResult), inputs)
    }

    @Test
    fun filter_array_of_strings_single_processors_all_strings_changed_check_affected_strings() {
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

    @Test
    fun filter_array_of_strings_multiple_processors_single_string_changed_check_array() {
        val firstInput = "input#1"
        val secondInput = "input#2"
        val secondIntermediateResult = "intermediateOutput#2"
        val secondInputResult = "output#2"
        val firstProcessor = mockk<Processor>()
        val secondProcessor = mockk<Processor>()
        every { firstProcessor.process(firstInput) } returns firstInput
        every { secondProcessor.process(firstInput) } returns firstInput
        every { firstProcessor.process(secondInput) } returns secondIntermediateResult
        every { secondProcessor.process(secondIntermediateResult) } returns secondInputResult
        val inputs = arrayOf<String?>(firstInput, secondInput)
        filter.process(inputs, arrayOf(firstProcessor, secondProcessor))
        assertArrayEquals(arrayOf(firstInput, secondInputResult), inputs)
    }

    @Test
    fun filter_array_of_strings_multiple_processors_single_string_changed_check_affected_strings() {
        val firstInput = "input#1"
        val secondInput = "input#2"
        val secondIntermediateResult = "intermediateOutput#2"
        val secondInputResult = "output#2"
        val firstProcessor = mockk<Processor>()
        val secondProcessor = mockk<Processor>()
        every { firstProcessor.process(firstInput) } returns firstInput
        every { secondProcessor.process(firstInput) } returns firstInput
        every { firstProcessor.process(secondInput) } returns secondIntermediateResult
        every { secondProcessor.process(secondIntermediateResult) } returns secondInputResult
        val inputs = arrayOf<String?>(firstInput, secondInput)
        val affectedStrings = filter.process(inputs, arrayOf(firstProcessor, secondProcessor))
        assertEquals(1, affectedStrings)
    }
}
