package fi.exa.cthulhuhelpper.persistence

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface TokenConfigurationDao {

    @Query("SELECT token, count FROM token_configuration")
    fun getConfiguration(): LiveData<List<TokenConfiguration>>

    @Update
    fun updateConfiguration(configs : List<TokenConfiguration>)
}