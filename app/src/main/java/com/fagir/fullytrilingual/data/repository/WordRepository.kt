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

    /**
     * Obtener palabras filtradas por idioma.
     * Dado que en WordDao ahora tenemos:
     *  - getWordsSpanish()    -> wordEs <> ''
     *  - getWordsEnglish()    -> wordEn <> ''
     *  - getWordsPortuguese() -> wordPt <> ''
     * Mapeamos "es", "en", "pt" a las consultas específicas.
     */
    suspend fun getWordsByLanguage(language: String): List<Word> {
        return when (language) {
            "es" -> wordDao.getWordsSpanish()
            "en" -> wordDao.getWordsEnglish()
            "pt" -> wordDao.getWordsPortuguese()
            else -> emptyList()
        }
    }

    // Eliminar una palabra por su ID
    suspend fun deleteWordById(id: Int) {
        wordDao.deleteWordById(id)
    }

    /**
     * Devuelve la palabra con el ID especificado (o null si no existe).
     * Requiere que el DAO tenga un método getWordById(id: Int).
     */
    suspend fun getWordById(id: Int): Word? {
        return wordDao.getWordById(id)
    }

    /**
     * Actualiza una palabra existente en la base de datos.
     * Requiere que el DAO tenga un método updateWord(word: Word).
     */
    suspend fun updateWord(word: Word) {
        wordDao.updateWord(word)
    }
}
