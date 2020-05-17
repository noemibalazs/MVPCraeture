package com.example.mvpcraeture.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mvpcraeture.helper.CREATURE_TABLE
import com.example.mvpcraeture.room.CreatureAttributesConverter

@Entity(tableName = CREATURE_TABLE)
data class Creature(
    @PrimaryKey
    val name: String,
    val drawable: Int,
    @TypeConverters(CreatureAttributesConverter::class)
    var attributes: CreatureAttributes? = null,
    val hitPoints: Int

)