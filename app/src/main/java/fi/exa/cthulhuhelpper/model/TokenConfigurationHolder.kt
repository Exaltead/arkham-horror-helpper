package fi.exa.cthulhuhelpper.model

import java.util.*

class TokenConfigurationHolder constructor(tokens: Map<CthulhuToken, Int>) {
    private val tokens: Map<CthulhuToken, Int>
    private val random = Random()
    private val expandedTokenPool: List<CthulhuToken>

    init {
        this.tokens = tokens.toSortedMap()
        expandedTokenPool = tokens.expandTokenPool()
    }

    fun getNewToken(): CthulhuToken?{

        if(expandedTokenPool.isEmpty()) return null
        return expandedTokenPool[random.nextInt(expandedTokenPool.size - 1)]
    }

    fun orderedTokenConfiguration(): List<Pair<CthulhuToken, Int>>{
        return tokens.map { it.toPair() }
    }

    fun sameConfig(other: TokenConfigurationHolder): Boolean{
        return tokens == other.tokens
    }

}

private fun Map<CthulhuToken, Int>.expandTokenPool(): List<CthulhuToken>{
    return this.flatMap { pair -> Array(pair.value){ pair.key}.asIterable() }
}