package fi.exa.cthulhuhelpper.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import fi.exa.cthulhuhelpper.model.CthulhuToken
import fi.exa.cthulhuhelpper.model.TokenConfigurationHolder
import javax.inject.Inject

class TokenViewModel @Inject constructor(): ViewModel(){

    private val config =  MutableLiveData<TokenConfigurationHolder>()
    private val currentToken = MutableLiveData<CthulhuToken>()
    init {
        config.value = TokenConfigurationHolder()
    }

    fun newToken() = currentToken.postValue(config.value?.getNewToken())

    fun getToken(): LiveData<CthulhuToken> = currentToken

    fun getTokenConfig(): LiveData<TokenConfigurationHolder> = config

    fun updateTokenConfig(token: CthulhuToken, newCount: Int){
        config.value?.updateWith(token, newCount)
        config.value = config.value
    }
}