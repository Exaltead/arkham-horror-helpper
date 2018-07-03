package fi.exa.cthulhuhelpper.model

import fi.exa.cthulhuhelpper.persistence.TokenConfiguration

enum class Difficulty { Easy, Standard, Hard, Expert }

object TokenConfigurationBuilder {
    fun fromList(tokens: List<TokenConfiguration>): TokenConfigurationHolder {
        return TokenConfigurationHolder(tokens.map { Pair(it.token, it.count) }.toMap())
    }

    fun stub(): TokenConfigurationHolder {
        return TokenConfigurationHolder(CthulhuToken.values()
                .mapIndexed { i, t -> Pair(t, i) }.toMap())
    }

    fun unwrap(tokenConfigurationHolder: TokenConfigurationHolder): List<TokenConfiguration> {
        return tokenConfigurationHolder
                .orderedTokenConfiguration()
                .map { TokenConfiguration(it.first, it.second) }
    }

    fun fromDificulty(difficulty: Difficulty): TokenConfigurationHolder{
        val tokens = when (difficulty) {
            Difficulty.Easy -> easyDifficulty()
            Difficulty.Standard -> standardDifficulty()
            Difficulty.Hard -> hardDifficulty()
            Difficulty.Expert -> expertDifficulty()
        }
        return TokenConfigurationHolder(tokens.plus(specials()))
    }
}


private fun easyDifficulty(): Map<CthulhuToken, Int>{
    return listOf(Pair(CthulhuToken.PLUS_1, 2), Pair(CthulhuToken.ZERO, 3),
            Pair(CthulhuToken.MINUS_1, 3), Pair(CthulhuToken.MINUS_2, 2))
            .toMap()
}

private fun standardDifficulty():Map<CthulhuToken, Int>{
    return listOf(Pair(CthulhuToken.PLUS_1, 1), Pair(CthulhuToken.ZERO, 2),
            Pair(CthulhuToken.MINUS_1, 3), Pair(CthulhuToken.MINUS_2, 2),
            Pair(CthulhuToken.MINUS_3, 1), Pair(CthulhuToken.MINUS_4, 1))
            .toMap()
}

private fun hardDifficulty():Map<CthulhuToken, Int>{
    return listOf( Pair(CthulhuToken.ZERO, 3),
            Pair(CthulhuToken.MINUS_1, 2), Pair(CthulhuToken.MINUS_2, 2),
            Pair(CthulhuToken.MINUS_3, 2), Pair(CthulhuToken.MINUS_4, 1),
            Pair(CthulhuToken.MINUS_5, 1)).toMap()
}

private fun expertDifficulty():Map<CthulhuToken, Int>{
    return listOf( Pair(CthulhuToken.ZERO, 1),
            Pair(CthulhuToken.MINUS_1, 2), Pair(CthulhuToken.MINUS_2, 2),
            Pair(CthulhuToken.MINUS_3, 2), Pair(CthulhuToken.MINUS_4, 2),
            Pair(CthulhuToken.MINUS_5, 1), Pair(CthulhuToken.MINUS_6, 1),
            Pair(CthulhuToken.MINUS_8, 1)).toMap()
 }

private fun specials(): Map<CthulhuToken, Int>{
    return mapOf(CthulhuToken.SKULL to 2, CthulhuToken.HOOD to 1,
            CthulhuToken.ELDER_SIGN to 1, CthulhuToken.FAILURE to 1)
}