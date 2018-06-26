package fi.exa.cthulhuhelpper.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import fi.exa.cthulhuhelpper.model.CthulhuToken
import fi.exa.cthulhuhelpper.model.TokenConfig

class TokenViewModel: ViewModel(){

    private val config =  MutableLiveData<TokenConfig>()
    private val currentToken = MutableLiveData<CthulhuToken>()
    init {
        config.value = TokenConfig()
    }

    fun newToken() = currentToken.postValue(config.value?.getNewToken())

    fun getToken(): LiveData<CthulhuToken> = currentToken

    fun getTokenConfig(): LiveData<TokenConfig> = config

    fun updateTokenConfig(token: CthulhuToken, newCount: Int){
        config.value?.updateWith(token, newCount)
        config.value = config.value
    }
}