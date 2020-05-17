package com.example.mvpcraeture.creaturehelper

import com.example.mvpcraeture.model.Creature
import com.example.mvpcraeture.model.CreatureAttributes
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CreatureGeneratorTest {

    private lateinit var creatureGenerator: CreatureGenerator

    @Before
    fun setUp(){
        creatureGenerator = CreatureGenerator()
    }

    @Test
    fun testGenerateHitPoints(){
        val attributes = CreatureAttributes(7, 3, 10)
        val name = "Alibaba"
        val expected = Creature(attributes = attributes, name = name, hitPoints = 84, drawable = 0)
        assertEquals(expected, creatureGenerator.generateCreature(attributes, name))
    }
}