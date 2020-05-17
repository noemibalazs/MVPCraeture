package com.example.mvpcraeture.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvpcraeture.model.Creature

@Database(entities = [Creature::class], version = 3, exportSchema = false)
@TypeConverters(CreatureAttributesConverter::class)
abstract class CreatureDataBase: RoomDatabase() {

    abstract fun getCreatureDao(): CreatureDAO
}