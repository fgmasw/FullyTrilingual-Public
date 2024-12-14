package com.fagir.fullytrilingual.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words") // Nombre de la tabla definido correctamente
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Clave primaria configurada
    val spanishWord: String,
    val englishWord: String,
    val portugueseWord: String,
    val spanishPhrase: String,
    val englishPhrase: String,
    val portuguesePhrase: String
)
