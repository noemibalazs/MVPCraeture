package com.example.mvpcraeture.di.component

import com.example.mvpcraeture.creaturehelper.SharedHelper
import com.example.mvpcraeture.di.module.AppModule
import com.example.mvpcraeture.presenter.CreaturePresenter
import com.example.mvpcraeture.room.CreatureDAO
import com.example.mvpcraeture.ui.CreatureActivity
import com.example.mvpcraeture.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun injectMain(mainActivity: MainActivity)
    fun injectCreature(creatureActivity: CreatureActivity)
}