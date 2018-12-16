package fi.exa.cthulhuhelpper.persistence

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PlayerDao {
    @Query("SELECT * FROM player")
    fun fetchPlayers(): LiveData<List<Player>>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertPlayer(player: Player)

    @Query("UPDATE player SET health = :health WHERE id = :id")
    fun updateHealth(id:Int, health: Int)
}