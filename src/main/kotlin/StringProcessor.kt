import processor.Processor

class StringProcessor {

    /**
     * String that is used instead of null values in process method.
     * It can be changed from any place, so setter is public.
     * Also, after the initial default value is changed by setter isDefaultChanged flag should become equal to true
     */
    var defaultString: String = ""
        set(value) {
            TODO()
        }

    /**
     * Flag to determine whether or not default string value was changed by setter.
     * It should be updated to true only when setter for defaultString is called
     */
    var isDefaultChanged: Boolean = false
        private set

    /**
     * Process single string with given Processor. In case string is null return value from property.
     * In case of null string replace it with defaultString property from the class
     * @param input string to process or null value
     * @param processors array of processors to apply to the input string
     * @return modified string by all given process from the input
     */
    fun process(input: String?, processors: Array<Processor>): String = TODO()

    /**
     * Process array of string by given processors.
     * Processing should be done in place, so original array of strings will be modified by given processor.
     * Function return affected strings in the array by the processor.
     * @param inputs array of strings to process
     * @param processors array of processors to apply to the each input string
     * @return number of input strings affected by at least one processor
     */
    fun process(inputs: Array<String?>, processors: Array<Processor>): Int = TODO()

}