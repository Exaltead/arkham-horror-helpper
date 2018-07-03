package fi.exa.cthulhuhelpper.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import fi.exa.cthulhuhelpper.model.CthulhuToken
import fi.exa.cthulhuhelpper.model.TokenConfigurationHolder
import fi.exa.cthulhuhelpper.repository.TokenConfigurationRepository
import javax.inject.Inject

class TokenViewModel @Inject constructor(
        private val tokenConfigurationRepository: TokenConfigurationRepository): ViewModel(){

    private val config: LiveData<TokenConfigurationHolder> = tokenConfigurationRepository.loadTokenConfiguration()
    private val currentToken = MutableLiveData<CthulhuToken>()

    fun newToken() = currentToken.postValue(config.value?.getNewToken())

    fun getToken(): LiveData<CthulhuToken> = currentToken

    fun getTokenConfig(): LiveData<TokenConfigurationHolder> = config

    fun updateTokenConfig(token: CthulhuToken, newCount: Int){
        tokenConfigurationRepository.updateTokenConfig(token, newCount)
    }
}