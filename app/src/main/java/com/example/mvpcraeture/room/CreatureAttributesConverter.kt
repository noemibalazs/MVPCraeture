package com.example.mvpcraeture.room

import androidx.room.TypeConverter
import com.example.mvpcraeture.model.Creature
import com.example.mvpcraeture.model.CreatureAttributes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CreatureAttributesConverter {

    @TypeConverter
    fun creatureAttributes2String(list: CreatureAttributes?):String?{
        if (list == null){
            return null
        }
        return Gson().toJson(list)
    }

    @TypeConverter
    fun string2CreatureAttributes(json:String?): CreatureAttributes?{
        if (json == null){
            return null
        }
        val type = object : TypeToken<CreatureAttributes>(){}.type
        return Gson().fromJson<CreatureAttributes>(json, type)
    }
}