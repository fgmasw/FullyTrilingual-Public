package com.fagir.fullytrilingual.data.repository

import com.fagir.fullytrilingual.data.local.dao.WordDao
import com.fagir.fullytrilingual.data.local.entities.Word

class WordRepository(private val wordDao: WordDao) {
    suspend fun insertWord(word: Word) {
        wordDao.insertWord(word)
    }

    suspend fun getAllWords(): List<Word> {
        return wordDao.getAllWords()
    }
}
