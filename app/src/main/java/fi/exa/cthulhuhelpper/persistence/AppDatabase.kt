package fi.exa.cthulhuhelpper.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(TokenConfiguration::class), (Player::class)], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun tokenConfigurationDao(): TokenConfigurationDao
    abstract fun playerDao(): PlayerDao
}