package com.example.mvpcraeture.presenter

import android.util.Log
import com.example.mvpcraeture.creaturehelper.AttributeStore
import com.example.mvpcraeture.creaturehelper.AttributeType
import com.example.mvpcraeture.creaturehelper.AttributeValue
import com.example.mvpcraeture.creaturehelper.CreatureGenerator
import com.example.mvpcraeture.model.Creature
import com.example.mvpcraeture.model.CreatureAttributes
import com.example.mvpcraeture.repository.CreateRepository

class CreaturePresenter(
    var generator: CreatureGenerator,
    val createRepository: CreateRepository
) : BasePresenter<CreatureContract.View>(), CreatureContract.Presenter {
    private lateinit var creature: Creature

    private var name = ""
    private var intelligence = 0
    private var endurance = 0
    private var strength = 0
    private var drawable = 0

    private fun updateCreature() {
        val attributes = CreatureAttributes(intelligence, strength, endurance)
        creature = generator.generateCreature(attributes, name, drawable)
        getView()?.showHitPoints(creature.hitPoints.toString())
    }

    override fun saveCreature() {
        if (canSaveCreature()) {
            createRepository.saveCreature(creature)
            getView()?.showCreatureSaved()
            Log.d("CP", "The saved creature is: $creature")
        } else {
            getView()?.showCreatureSaveError()
        }
    }

    override fun updateName(name: String) {
        this.name = name
        updateCreature()
    }

    override fun addAttributeSelected(attributeType: AttributeType, position: Int) {
        when (attributeType) {
            AttributeType.ENDURANCE ->
                endurance = AttributeStore.ENDURANCE[position].value
            AttributeType.STRENGTH ->
                strength = AttributeStore.STRENGTH[position].value
            AttributeType.INTELLIGENCE ->
                intelligence = AttributeStore.INTELLIGENCE[position].value
        }
        updateCreature()
    }

    override fun isDrawableSelected(): Boolean {
        return drawable != 0
    }

    override fun drawableSelected(drawable: Int) {
        this.drawable = drawable
        getView()?.showAvatarDrawable(drawable)
        updateCreature()
    }

    private fun canSaveCreature(): Boolean {
        return name.isNotEmpty() && isDrawableSelected() && endurance != 0 && intelligence != 0 && strength != 0
    }
}