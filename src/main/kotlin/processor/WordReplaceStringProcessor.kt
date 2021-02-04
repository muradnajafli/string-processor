package processor

class WordReplaceStringProcessor(
    private val substringToReplace: String,
    private val replacement: String
) : Processor {

    /**
     * Replace specific substring in the original string. All occurrences of the substring should be replaced.
     * Use properties substringToReplace
     * @param input origin string
     * @return string that removes all occurrences of substring in the original string
     */
    override fun process(input: String): String = TODO()
}