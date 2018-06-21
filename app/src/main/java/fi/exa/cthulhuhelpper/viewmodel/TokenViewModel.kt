package fi.exa.cthulhuhelpper.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import fi.exa.cthulhuhelpper.model.CthulhuToken
import fi.exa.cthulhuhelpper.model.TokenConfig

class TokenViewModel: ViewModel(){

    val config =  TokenConfig()

    private val currentToken = MutableLiveData<CthulhuToken>()


    fun newToken(){
        currentToken.postValue(config.getNewToken())
    }

    fun getToken(): LiveData<CthulhuToken> {
        return currentToken
    }
}