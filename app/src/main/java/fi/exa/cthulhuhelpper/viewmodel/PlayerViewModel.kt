package fi.exa.cthulhuhelpper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import fi.exa.cthulhuhelpper.persistence.Player
import fi.exa.cthulhuhelpper.repository.PlayerRepository
import javax.inject.Inject

class PlayerViewModel @Inject constructor(
        private val playerRepository: PlayerRepository): ViewModel(){

    private val player: LiveData<Player> = playerRepository.loadPlayer()

    val health: LiveData<String> = Transformations.map(player) { it.health.toString() }
    val sanity: LiveData<String> = Transformations.map(player) { it.sanity.toString() }
    val resource: LiveData<String> = Transformations.map(player) { it.resources.toString() }

    fun adjustHealthBy(adjustment: Int){
        player.value?.let {
            val newHealth = it.health + adjustment
            if(newHealth in 1..10){
                playerRepository.setHealth(it.id, newHealth)
            }
        }
    }

    fun adjustSanityBy(adjustment: Int){
        player.value?.let {
            val sanity = it.sanity + adjustment
            if(sanity in 1..10){
                playerRepository.setSanity(it.id, sanity)
            }
        }
    }

    fun adjustResourcesBy(adjustment: Int){
        player.value?.let {
            val resources = it.resources + adjustment
            if(resources in 1..20){
                playerRepository.setResources(it.id, resources)
            }
        }
    }

}