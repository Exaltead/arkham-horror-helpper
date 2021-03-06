package fi.exa.cthulhuhelpper.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import fi.exa.cthulhuhelpper.model.*
import fi.exa.cthulhuhelpper.persistence.TokenConfiguration
import fi.exa.cthulhuhelpper.persistence.TokenConfigurationDao
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class TokenConfigurationRepository @Inject constructor(
        private val tokenDao: TokenConfigurationDao) {


    fun loadTokenConfiguration(): LiveData<TokenConfigurationHolder> {
        return Transformations.map(tokenDao.getConfiguration())
            { tokens -> ensureTokensExisting(tokens)}
    }

    fun insertTokenConfigs(tokens: TokenConfigurationHolder){
        doAsync {
            tokenDao.insertConfiguration(TokenConfigurationBuilder.unwrap(tokens))
        }
    }

    fun updateTokenConfig(cthulhuToken: CthulhuToken, count: Int) {
        val token = TokenConfiguration()
        token.count = count
        token.token = cthulhuToken
        doAsync {
            tokenDao.updateConfiguration(token)
        }
    }

    private fun ensureTokensExisting(tokens: List<TokenConfiguration>): TokenConfigurationHolder {
        if(tokens.isNotEmpty()){
            return TokenConfigurationBuilder.fromList(tokens)
        }
        val stub = TokenConfigurationBuilder.stub()
        doAsync {
            insertTokenConfigs(stub)
        }
        return stub

    }

}
