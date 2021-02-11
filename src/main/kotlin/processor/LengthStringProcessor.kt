package processor

class LengthStringProcessor : Processor {
    /**
     * Add additional word to the original string depending on the string length:
     * 1) If string length is in range from 0 to 5 inclusively add 'short ' prefix to it
     * 2) If string length is in range from 6 to 10 inclusively add 'medium ' prefix to it
     * 3) If string length is in range from 11 to 20 inclusively add 'long ' prefix to it
     * 4) Otherwise do not modify string
     * @param input original string
     * @return Modified string depending on the string length
     */
    override fun process(input: String): String = TODO()
}