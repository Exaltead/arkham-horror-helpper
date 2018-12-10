package fi.exa.cthulhuhelpper.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(TokenConfiguration::class)], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun tokenConfigurationDao(): TokenConfigurationDao
}