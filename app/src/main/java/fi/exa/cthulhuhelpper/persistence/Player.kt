package fi.exa.cthulhuhelpper.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Player(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var health: Int,
    var sanity: Int,
    var resources: Int
)