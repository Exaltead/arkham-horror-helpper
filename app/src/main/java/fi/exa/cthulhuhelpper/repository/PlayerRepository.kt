package fi.exa.cthulhuhelpper.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import fi.exa.cthulhuhelpper.persistence.Player
import fi.exa.cthulhuhelpper.persistence.PlayerDao
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class PlayerRepository @Inject constructor(private val playerDao: PlayerDao) {

    fun loadPlayer(): LiveData<Player> = Transformations.map(playerDao.fetchPlayers()) {
        if(it.isEmpty()){
            doAsync { playerDao.insertPlayer(defaultPlayer()) }
        }
        it.firstOrNull()?: defaultPlayer()
    }

    fun setHealth(id: Int, health: Int){
        doAsync { playerDao.updateHealth(id, health) }
    }

    fun setSanity(id: Int, sanity: Int) {
        doAsync { playerDao.updateSanity(id, sanity) }
    }

    fun setResources(id: Int, resources: Int){
        doAsync { playerDao.updateResources(id, resources) }
    }

    private fun defaultPlayer(): Player = Player(1, 5, 5, 5)
}