package fi.exa.cthulhuhelpper.model

import java.util.*

class TokenConfigurationHolder constructor(val tokens: List<Pair<CthulhuToken, Int>>) {
    private val random = Random()
    private val expandedTokenPool: List<CthulhuToken> = tokens.expandTokenPool()

    fun getNewToken(): CthulhuToken{
        // For now ignore that each token might have different distribution
        return expandedTokenPool[random.nextInt(expandedTokenPool.size - 1)]
    }

}

private fun List<Pair<CthulhuToken, Int>>.expandTokenPool(): List<CthulhuToken>{
    return this.flatMap { pair -> Array(pair.second){ pair.first}.asIterable() }
}