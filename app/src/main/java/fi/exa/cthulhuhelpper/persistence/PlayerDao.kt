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

    @Query("UPDATE player SET sanity = :sanity WHERE id = :id")
    fun updateSanity(id:Int, sanity: Int)

    @Query("UPDATE player SET resources = :resources WHERE id = :id")
    fun updateResources(id:Int, resources: Int)
}