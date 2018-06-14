package fi.exa.cthulhuhelpper.model

import java.util.*

class Token(random: Random) {
    private val tokenMapping = listOf("E", "+1", "0", "-1", "-2", "-3", "-4", "H", "Sq", "Sk", "T")
    private val tokenNumber: Int
    init {
        tokenNumber = random.nextInt(tokenMapping.size-1)
    }

    fun getTextValue(): String{
        return tokenMapping[tokenNumber];
    }
}