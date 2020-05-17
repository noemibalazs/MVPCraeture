package com.example.mvpcraeture.presenter

import androidx.lifecycle.LiveData
import com.example.mvpcraeture.model.Creature
import com.example.mvpcraeture.repository.CreateRepository

class AllCreaturePresenter(
    private val createRepository: CreateRepository
) : BasePresenter<AllCreatureContract.View>(), AllCreatureContract.Presenter {

    override fun getAllCreatures(): LiveData<MutableList<Creature>>? {
        return createRepository.getAllCreatures()
    }

    override fun clearCreature(creature: Creature) {
        if (creatureIsValid(creature)) {
            createRepository.clearCreatures(creature)
            getView()?.showCreatureCleared()
        } else {
            getView()?.showCreatureClearError()
        }
    }

    private fun creatureIsValid(creature: Creature): Boolean {
        return creature.name.isNotEmpty()
    }
}