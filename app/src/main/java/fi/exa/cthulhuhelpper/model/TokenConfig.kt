package fi.exa.cthulhuhelpper.model

import java.util.*

class TokenConfig {
    private val random = Random()
    val tokens = listOf("E", "+1", "0", "-1", "-2", "-3", "-4", "H", "Sq", "Sk", "T")
            .map {  it to 0 }
}