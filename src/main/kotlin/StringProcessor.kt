import processor.Processor

class StringProcessor {

    /**
     * String that is used instead of null values in process method.
     * It can be changed from any place, so setter is public.
     * Also, after the initial default value is changed by setter isDefaultChanged flag should become equal to true
     */
    var defaultString: String = ""
        set(value) {
            field = value
            isDefaultChanged = true
        }

    /**
     * Flag to determine whether or not default string value was changed by setter.
     * It should be updated to true only when setter for defaultString is called
     */
    var isDefaultChanged: Boolean = false
        private set

    /**
     * Process single string with given Processor. In case modified string is null return value from property.
     * In case of input string is null replace it with defaultString property from the class
     * @param input string to process or null value
     * @param processors array of processors to apply to the input string
     * @return modified string by all given process from the input
     */
    fun process(input: String?, processors: Array<Processor>): String {
        val processedInput = input ?: defaultString
        var modifiedInput = processedInput

        if (processedInput != defaultString) {
            for (processor in processors) {
                modifiedInput = processor.process(modifiedInput)
            }

        }

        return modifiedInput
    }

    /**
     * Process array of string by the given processors.
     * Processing should be done in place, so the original array of strings will be modified by the given processor.
     * Function return affected strings in the array by the processor.
     * @param inputs array of strings to process
     * @param processors array of processors to apply to each input string
     * @return number of input strings affected by at least one processor
     */
    fun process(inputs: Array<String?>, processors: Array<Processor>): Int {
        var affectedCount = 0

        for (i in inputs.indices) {
            if (inputs[i] != null) {
                val modifiedInput = process(inputs[i], processors)
                if (modifiedInput != inputs[i]) {
                    inputs[i] = modifiedInput
                    affectedCount++
                }
            }

        }

        return affectedCount

    }
}