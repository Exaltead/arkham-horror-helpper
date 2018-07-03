package fi.exa.cthulhuhelpper.model

import java.util.*

class TokenConfigurationHolder constructor(val tokens: List<Pair<CthulhuToken, Int>>) {
    private val random = Random()

    fun getNewToken(): CthulhuToken{
        // For now ignore that each token might have different distribution
        return tokens[random.nextInt(tokens.size - 1)].first
    }
}