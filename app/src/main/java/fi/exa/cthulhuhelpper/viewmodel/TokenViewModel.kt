package fi.exa.cthulhuhelpper.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import fi.exa.cthulhuhelpper.model.CthulhuToken
import fi.exa.cthulhuhelpper.model.Difficulty
import fi.exa.cthulhuhelpper.model.TokenConfigurationBuilder
import fi.exa.cthulhuhelpper.model.TokenConfigurationHolder
import fi.exa.cthulhuhelpper.repository.TokenConfigurationRepository
import javax.inject.Inject

class TokenViewModel @Inject constructor(
        private val tokenConfigurationRepository: TokenConfigurationRepository): ViewModel(){

    private val config: LiveData<TokenConfigurationHolder> = tokenConfigurationRepository.loadTokenConfiguration()
    private val currentToken = MutableLiveData<CthulhuToken>()

    init {
        config.observeForever { t -> t?.let { currentHolder = it } }
    }
    private lateinit var currentHolder: TokenConfigurationHolder

    fun newToken() {
        currentToken.value = currentHolder.getNewToken()
    }

    fun getToken(): LiveData<CthulhuToken> = currentToken

    fun getTokenConfig(): LiveData<TokenConfigurationHolder> = config

    fun updateTokenConfig(token: CthulhuToken, newCount: Int){
        if(newCount in 0..5){
            tokenConfigurationRepository.updateTokenConfig(token, newCount)
        }
    }

    fun setDifficulty(difficulty: Difficulty){
        val holder = TokenConfigurationBuilder.fromDifficulty(difficulty)
        tokenConfigurationRepository.insertTokenConfigs(holder)
    }
}