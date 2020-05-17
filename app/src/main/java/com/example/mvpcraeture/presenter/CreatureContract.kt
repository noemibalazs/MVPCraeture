package com.example.mvpcraeture.presenter

import androidx.annotation.DrawableRes
import com.example.mvpcraeture.creaturehelper.AttributeType

interface CreatureContract {

    interface Presenter{
        fun updateName(name: String)
        fun addAttributeSelected(attributeType: AttributeType, position:Int)
        fun isDrawableSelected(): Boolean
        fun drawableSelected(drawable: Int)
        fun saveCreature()
    }

    interface View{
        fun showHitPoints(hitPoints: String)
        fun showAvatarDrawable(@DrawableRes resourceID: Int)
        fun showCreatureSaved()
        fun showCreatureSaveError()
    }
}