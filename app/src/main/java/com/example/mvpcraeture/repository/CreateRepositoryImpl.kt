package com.example.mvpcraeture.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mvpcraeture.model.Creature
import com.example.mvpcraeture.room.CreatureDAO
import org.jetbrains.anko.doAsync
import java.util.concurrent.Executors

class CreateRepositoryImpl(val creatureDAO: CreatureDAO?) : CreateRepository {

    override fun saveCreature(creature: Creature) {
        doAsync {
            creatureDAO?.let {
                it.addCreature(creature)
                Log.d("Save creature", "creature was saved: ${creature.name}")
            }
        }
    }

    override fun getAllCreatures(): LiveData<MutableList<Creature>>? {
        return creatureDAO?.getAllCreatures()
    }

    override fun clearCreatures(creature: Creature) {
        Executors.newSingleThreadExecutor().execute {
            creatureDAO?.clearCreatures(creature)
        }
    }

    override fun findCreature(name: String): Creature {
        var creature: Creature? = null
        doAsync {
            creature = creatureDAO?.findCreature(name)
        }
        return creature!!
    }
}