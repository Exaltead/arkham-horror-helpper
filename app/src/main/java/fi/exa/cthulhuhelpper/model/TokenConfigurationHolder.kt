package fi.exa.cthulhuhelpper.model

import java.util.*

class TokenConfigurationHolder {
    private val random = Random()
    val tokens =  CthulhuToken.values().map {  it to 0 }.toMutableList()

    fun getNewToken(): CthulhuToken{
        // For now ignore that each token might have different distribution
        return tokens[random.nextInt(tokens.size - 1)].first
    }

    fun updateWith(token: CthulhuToken, newValue: Int){
        val tokenIndex = tokens.indexOfFirst { t -> t.first == token }
        tokens[tokenIndex] = Pair(token, newValue)
    }
}