package com.example.mvpcraeture.repository

import androidx.lifecycle.LiveData
import com.example.mvpcraeture.model.Creature

interface CreateRepository {

    fun saveCreature(creature: Creature)
    fun getAllCreatures(): LiveData<MutableList<Creature>>?
    fun clearCreatures(creature: Creature)
    fun findCreature(name:String):Creature
}