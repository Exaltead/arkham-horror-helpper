package fi.exa.cthulhuhelpper.model

import fi.exa.cthulhuhelpper.persistence.TokenConfiguration

enum class Difficulty { Easy, Standard, Hard, Expert }

object TokenConfigurationBuilder {
    fun fromList(tokens: List<TokenConfiguration>): TokenConfigurationHolder {
        return TokenConfigurationHolder(tokens.map { Pair(it.token, it.count) })
    }

    fun stub(): TokenConfigurationHolder {
        return TokenConfigurationHolder(CthulhuToken.values().mapIndexed { i, t -> Pair(t, i) })
    }

    fun upwrap(tokenConfigurationHolder: TokenConfigurationHolder): List<TokenConfiguration> {
        return tokenConfigurationHolder.tokens.map { it.toTokenConfiguration() }
    }

    fun fromDificulty(difficulty: Difficulty): TokenConfigurationHolder{
        val tokens = when (difficulty) {
            Difficulty.Easy -> easyDifficulty()
            Difficulty.Standard -> standardDifficulty()
            Difficulty.Hard -> hardDifficulty()
            Difficulty.Expert -> expertDifficulty()
        }
        return TokenConfigurationHolder(tokens.addSpecials())
    }
}

private fun Pair<CthulhuToken, Int>.toTokenConfiguration() : TokenConfiguration{
    val tokenConfiguration = TokenConfiguration()
    tokenConfiguration.token = this.first
    tokenConfiguration.count = this.second
    return tokenConfiguration
}

private fun easyDifficulty(): List<Pair<CthulhuToken, Int>>{
    return listOf(Pair(CthulhuToken.PLUS_1, 2), Pair(CthulhuToken.ZERO, 3),
            Pair(CthulhuToken.MINUS_1, 3), Pair(CthulhuToken.MINUS_2, 2))
}

private fun standardDifficulty():List<Pair<CthulhuToken, Int>>{
    return listOf(Pair(CthulhuToken.PLUS_1, 1), Pair(CthulhuToken.ZERO, 2),
            Pair(CthulhuToken.MINUS_1, 3), Pair(CthulhuToken.MINUS_2, 2),
            Pair(CthulhuToken.MINUS_3, 1), Pair(CthulhuToken.MINUS_4, 1))
}

private fun hardDifficulty():List<Pair<CthulhuToken, Int>>{
    return listOf( Pair(CthulhuToken.ZERO, 3),
            Pair(CthulhuToken.MINUS_1, 2), Pair(CthulhuToken.MINUS_2, 2),
            Pair(CthulhuToken.MINUS_3, 2), Pair(CthulhuToken.MINUS_4, 1),
            Pair(CthulhuToken.MINUS_5, 1))
}

private fun expertDifficulty():List<Pair<CthulhuToken, Int>>{
    return listOf( Pair(CthulhuToken.ZERO, 1),
            Pair(CthulhuToken.MINUS_1, 2), Pair(CthulhuToken.MINUS_2, 2),
            Pair(CthulhuToken.MINUS_3, 2), Pair(CthulhuToken.MINUS_4, 2),
            Pair(CthulhuToken.MINUS_5, 1), Pair(CthulhuToken.MINUS_6, 1),
            Pair(CthulhuToken.MINUS_8, 1))
 }

private fun List<Pair<CthulhuToken, Int>>.addSpecials(): List<Pair<CthulhuToken, Int>>{
    val specials = listOf(Pair(CthulhuToken.SKULL, 2), Pair(CthulhuToken.HOOD, 1),
            Pair(CthulhuToken.ELDER_SIGN, 1), Pair(CthulhuToken.FAILURE, 1))
    return listOf(this, specials).flatten()
}