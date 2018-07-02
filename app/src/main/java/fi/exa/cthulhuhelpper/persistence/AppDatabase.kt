package fi.exa.cthulhuhelpper.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [(TokenConfiguration::class)], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun tokenConfigurationDao(): TokenConfigurationDao
}