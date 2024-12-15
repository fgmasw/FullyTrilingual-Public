package com.fagir.fullytrilingual.data.repository

import com.fagir.fullytrilingual.data.local.dao.WordDao
import com.fagir.fullytrilingual.data.local.entities.Word

class WordRepository(private val wordDao: WordDao) {

    // Insertar una nueva palabra
    suspend fun insertWord(word: Word): Long {
        return wordDao.insertWord(word)
    }

    // Obtener todas las palabras
    suspend fun getAllWords(): List<Word> {
        return wordDao.getAllWords()
    }

    // Obtener palabras filtradas por idioma
    suspend fun getWordsByLanguage(language: String): List<Word> {
        return wordDao.getWordsByLanguage(language)
    }

    // Eliminar una palabra por su ID
    suspend fun deleteWordById(id: Int) {
        wordDao.deleteWordById(id)
    }
}
