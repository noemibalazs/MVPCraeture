package com.example.mvpcraeture.creaturehelper

data class AttributeValue(
    var name: String,
    val value: Int
){
    override fun toString(): String {
        return "$name: $value"
    }
}