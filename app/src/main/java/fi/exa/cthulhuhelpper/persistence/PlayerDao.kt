package fi.exa.cthulhuhelpper.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface PlayerDao {
    @Query("SELECT * FROM player")
    fun fetchPlayers(): LiveData<List<Player>>
}