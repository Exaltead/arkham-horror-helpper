package fi.exa.cthulhuhelpper.model

import java.util.*

class TokenConfigurationHolder constructor(tokens: Map<CthulhuToken, Int>) {
    private val tokens: Map<CthulhuToken, Int>
    private val random = Random()
    private val expandedTokenPool: List<CthulhuToken> = tokens.expandTokenPool()

    init {
        this.tokens = tokens.toSortedMap()
    }

    fun getNewToken(): CthulhuToken{
        // For now ignore that each token might have different distribution
        return expandedTokenPool[random.nextInt(expandedTokenPool.size - 1)]
    }

    fun orderedTokenConfiguration(): List<Pair<CthulhuToken, Int>>{
        return tokens.map { it.toPair() }
    }

}

private fun Map<CthulhuToken, Int>.expandTokenPool(): List<CthulhuToken>{
    return this.flatMap { pair -> Array(pair.value){ pair.key}.asIterable() }
}