package com.example.mvpcraeture.adapter

import com.example.mvpcraeture.model.Creature

interface CreatureListener {

    fun onCreatureClicked(creature: Creature)
}