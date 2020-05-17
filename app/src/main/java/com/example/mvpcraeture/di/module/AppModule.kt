package com.example.mvpcraeture.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.mvpcraeture.creaturehelper.CreatureGenerator
import com.example.mvpcraeture.creaturehelper.SharedHelper
import com.example.mvpcraeture.helper.CREATURE_DATA_BASE
import com.example.mvpcraeture.presenter.CreaturePresenter
import com.example.mvpcraeture.repository.CreateRepository
import com.example.mvpcraeture.repository.CreateRepositoryImpl
import com.example.mvpcraeture.room.CreatureDAO
import com.example.mvpcraeture.room.CreatureDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun getApplicationContext(): Context = app

    @Provides
    @Singleton
    fun getCreatureDataBase(): CreatureDataBase {
        return Room.databaseBuilder(
            app.applicationContext,
            CreatureDataBase::class.java,
            CREATURE_DATA_BASE
        ).build()
    }

    @Provides
    @Singleton
    fun getCreatureDAO(db: CreatureDataBase): CreatureDAO {
        return db.getCreatureDao()
    }

    @Provides
    @Singleton
    fun getSharedPref(): SharedHelper {
        return SharedHelper(app.applicationContext)
    }

    @Provides
    @Singleton
    fun getCreateRepository(dao: CreatureDAO): CreateRepository{
        return CreateRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun getCreatureGenerator(): CreatureGenerator{
        return CreatureGenerator()
    }

    @Provides
    @Singleton
    fun getCreaturePresenter(generator: CreatureGenerator, repository: CreateRepository): CreaturePresenter{
        return CreaturePresenter(generator, repository)
    }
}