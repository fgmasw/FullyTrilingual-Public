package com.fagir.fullytrilingual.data.repository

import com.fagir.fullytrilingual.data.local.dao.WordDao
import com.fagir.fullytrilingual.data.local.entities.Word

class WordRepository(private val wordDao: WordDao) {
    suspend fun insertWord(word: Word): Long {
        return wordDao.insertWord(word)
    }

    suspend fun getWordsByLanguage(language: String): List<Word> {
        return wordDao.getWordsByLanguage(language)
    }

    suspend fun getAllWords(): List<Word> {
        return wordDao.getAllWords()
    }

    suspend fun deleteWordById(id: Int) {
        wordDao.deleteWordById(id)
    }
}
