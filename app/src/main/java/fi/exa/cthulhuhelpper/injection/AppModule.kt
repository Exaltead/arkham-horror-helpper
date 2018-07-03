package fi.exa.cthulhuhelpper.injection

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import fi.exa.cthulhuhelpper.persistence.AppDatabase
import fi.exa.cthulhuhelpper.persistence.TokenConfigurationDao
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun providesDatabase(app: Application): AppDatabase{
        return Room.databaseBuilder(app, AppDatabase::class.java,"cthulhu-database").build()
    }

    @Provides
    fun providesTokenDao(appDatabase: AppDatabase): TokenConfigurationDao{
        return appDatabase.tokenConfigurationDao()
    }
}