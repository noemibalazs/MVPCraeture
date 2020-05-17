package com.example.mvpcraeture.creaturehelper

import com.example.mvpcraeture.model.Creature
import com.example.mvpcraeture.model.CreatureAttributes

open class CreatureGenerator {

   open fun generateCreature(creatureAttributes: CreatureAttributes, name: String ="", drawable: Int=0): Creature {
        val hitPoints =
            5 * creatureAttributes.intelligence + 3 * creatureAttributes.strength + 4 * creatureAttributes.endurance
        return Creature(
            attributes = creatureAttributes,
            hitPoints = hitPoints,
            name = name,
            drawable = drawable
        )
    }
}