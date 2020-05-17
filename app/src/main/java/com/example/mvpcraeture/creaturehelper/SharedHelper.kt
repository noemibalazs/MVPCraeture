package com.example.mvpcraeture.creaturehelper

import android.content.Context
import com.example.mvpcraeture.helper.CREATURE_KEY
import com.example.mvpcraeture.helper.CREATURE_PREFERENCES

class SharedHelper(val context: Context) {

    private val pref = context.getSharedPreferences(CREATURE_PREFERENCES, Context.MODE_PRIVATE)

    fun saveCreatureName(name: String) {
        pref.edit().putString(CREATURE_KEY, name).apply()
    }

    fun getCreatureName(): String {
        return pref.getString(CREATURE_KEY, "") ?: ""
    }
}