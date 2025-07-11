package com.hannes.guitarbookapp.enums

enum class SongStatus(val displayName: String) {
    WANT_TO_LEARN("Want to learn"),
    LEARNING("Learning"),
    MASTERED("Mastered");

    override fun toString(): String = displayName
}