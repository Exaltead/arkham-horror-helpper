package fi.exa.cthulhuhelpper.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import fi.exa.cthulhuhelpper.model.Token
import java.util.*

class TokenViewModel: ViewModel(){
    private val random = Random()
    private val currentToken = MutableLiveData<Token>()


    fun newToken(){
        currentToken.postValue(Token(random))
    }

    fun getToken(): LiveData<Token> {
        return currentToken
    }
}