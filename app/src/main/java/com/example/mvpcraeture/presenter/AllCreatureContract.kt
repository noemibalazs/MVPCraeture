package com.example.mvpcraeture.presenter

import androidx.lifecycle.LiveData
import com.example.mvpcraeture.model.Creature

interface AllCreatureContract {

    interface Presenter{
        fun getAllCreatures():LiveData<MutableList<Creature>>?
        fun clearCreature(creature:Creature)
    }

    interface View{
        fun showCreatureCleared()
        fun showCreatureClearError()
    }
}