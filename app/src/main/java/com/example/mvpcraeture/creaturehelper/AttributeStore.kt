package com.example.mvpcraeture.creaturehelper

class AttributeStore {

    companion object {

        val INTELLIGENCE = listOf(
            AttributeValue("None", 0),
            AttributeValue("Aristotle", 3),
            AttributeValue("Newton", 7),
            AttributeValue("Einstein", 10)
        )

        val ENDURANCE = listOf(
            AttributeValue("None", 0),
            AttributeValue("Thor", 4),
            AttributeValue("Hulk", 8),
            AttributeValue("Hercules", 10)
        )

        val STRENGTH = listOf(
            AttributeValue("None", 0),
            AttributeValue("Aluminium", 4),
            AttributeValue("Gold", 6),
            AttributeValue("Iron", 10)
        )
    }
}
