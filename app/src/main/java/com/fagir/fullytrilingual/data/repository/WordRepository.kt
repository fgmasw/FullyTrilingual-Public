package com.fagir.fullytrilingual.data.repository

import com.fagir.fullytrilingual.data.local.dao.WordDao
import com.fagir.fullytrilingual.data.local.entities.Word

class WordRepository(private val wordDao: WordDao) {

    // Inserta una nueva palabra en la base de datos.
    suspend fun insertWord(word: Word): Long {
        return wordDao.insertWord(word)
    }

    // Retorna todas las palabras guardadas.
    suspend fun getAllWords(): List<Word> {
        return wordDao.getAllWords()
    }

    // Devuelve palabras según el idioma:
    // es -> palabra en español
    // en -> palabra en inglés
    // pt -> palabra en portugués
    suspend fun getWordsByLanguage(language: String): List<Word> {
        return when (language) {
            "es" -> wordDao.getWordsSpanish()
            "en" -> wordDao.getWordsEnglish()
            "pt" -> wordDao.getWordsPortuguese()
            else -> emptyList()
        }
    }

    // Borra una palabra de la base de datos según su ID.
    suspend fun deleteWordById(id: Int) {
        wordDao.deleteWordById(id)
    }

    // Busca una palabra según su ID. Si no existe, regresa null.
    suspend fun getWordById(id: Int): Word? {
        return wordDao.getWordById(id)
    }

    // Actualiza los datos de una palabra en la base de datos.
    suspend fun updateWord(word: Word) {
        wordDao.updateWord(word)
    }
}
