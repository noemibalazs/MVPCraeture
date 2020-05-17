package com.example.mvpcraeture.presenter

import com.example.mvpcraeture.creaturehelper.AttributeType
import com.example.mvpcraeture.creaturehelper.CreatureGenerator
import com.example.mvpcraeture.model.Creature
import com.example.mvpcraeture.model.CreatureAttributes
import com.example.mvpcraeture.repository.CreateRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CreaturePresenterTest {

    private lateinit var presenter: CreaturePresenter
    @Mock
    lateinit var view: CreatureContract.View
    @Mock
    lateinit var mockGenerator: CreatureGenerator
    @Mock
    lateinit var mockRepository: CreateRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = CreaturePresenter(mockGenerator, mockRepository)
        presenter.setView(view)
    }

    @Test
    fun testIntelligence() {
        val attributes = CreatureAttributes(10,  0,  0)
        val stubCreature =
            Creature(attributes = attributes, hitPoints = 50, name = "", drawable = 0)
        `when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)
        presenter.addAttributeSelected(AttributeType.INTELLIGENCE, 3)
        verify(view, times(1)).showHitPoints("50")
    }

    @Test
    fun testEndurance(){
        val attributes = CreatureAttributes(0,  0,  10)
        val stubCreature =
            Creature(attributes = attributes, hitPoints = 40, name = "", drawable = 0)
        `when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)
        presenter.addAttributeSelected(AttributeType.ENDURANCE, 3)
        verify(view, times(1)).showHitPoints("40")
    }

    @Test
    fun testStrengths(){
        val attributes = CreatureAttributes(0,  10,  0)
        val stubCreature =
            Creature(attributes = attributes, hitPoints = 30, name = "", drawable = 0)
        `when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)
        presenter.addAttributeSelected(AttributeType.STRENGTH, 3)
        verify(view, times(1)).showHitPoints("30")
    }
}
