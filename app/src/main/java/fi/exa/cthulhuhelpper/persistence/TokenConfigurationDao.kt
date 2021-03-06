package fi.exa.cthulhuhelpper.persistence

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TokenConfigurationDao {

    @Query("SELECT token, count FROM token_configuration")
    fun getConfiguration(): LiveData<List<TokenConfiguration>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConfiguration(configs: List<TokenConfiguration>)

    @Update
    fun updateConfiguration(configs : List<TokenConfiguration>)

    @Update
    fun updateConfiguration(config: TokenConfiguration)
}