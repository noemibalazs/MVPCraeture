package com.example.mvpcraeture.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvpcraeture.model.Creature

@Dao
interface CreatureDAO {

    @Query("SELECT * FROM creature")
    fun getAllCreatures(): LiveData<MutableList<Creature>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCreature(creature: Creature)

    @Delete
    fun clearCreatures(creature: Creature)

    @Query("SELECT * FROM creature WHERE name =:name")
    fun findCreature(name: String): Creature

    @Query("SELECT COUNT(name) FROM creature")
    fun getRows(): LiveData<Int>
}