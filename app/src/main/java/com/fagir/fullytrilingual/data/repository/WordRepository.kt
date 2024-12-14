package com.fagir.fullytrilingual.data.repository

import com.fagir.fullytrilingual.data.local.dao.WordDao
import com.fagir.fullytrilingual.data.local.entities.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    suspend fun insertWord(word: Word) {
        wordDao.insertWord(word)
    }

    fun getAllWords(): Flow<List<Word>> {
        return wordDao.getAllWords()
    }
}
