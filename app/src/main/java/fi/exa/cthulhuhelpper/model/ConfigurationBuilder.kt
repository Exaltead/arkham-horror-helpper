package fi.exa.cthulhuhelpper.model

import fi.exa.cthulhuhelpper.persistence.TokenConfiguration

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
}

private fun Pair<CthulhuToken, Int>.toTokenConfiguration() : TokenConfiguration{
    val tokenConfiguration = TokenConfiguration()
    tokenConfiguration.token = this.first
    tokenConfiguration.count = this.second
    return tokenConfiguration
}