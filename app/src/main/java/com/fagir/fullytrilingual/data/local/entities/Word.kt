package com.fagir.fullytrilingual.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val baseLanguage: String,
    val word: String,
    val translation1: String,
    val translation2: String,
    val phraseBase: String,
    val phraseTranslation1: String,
    val phraseTranslation2: String
)
