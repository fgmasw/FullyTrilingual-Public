package com.fagir.fullytrilingual.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    // Palabra en Español
    val wordEs: String,

    // Palabra en Inglés
    val wordEn: String,

    // Palabra en Portugués
    val wordPt: String,

    // Frase de ejemplo en Español
    val phraseEs: String,

    // Frase de ejemplo en Inglés
    val phraseEn: String,

    // Frase de ejemplo en Portugués
    val phrasePt: String
)
