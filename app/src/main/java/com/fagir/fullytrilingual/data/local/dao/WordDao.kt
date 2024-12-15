package com.fagir.fullytrilingual.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fagir.fullytrilingual.data.local.entities.Word

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word): Long

    @Query("SELECT * FROM words WHERE baseLanguage = :language")
    suspend fun getWordsByLanguage(language: String): List<Word>

    @Query("SELECT * FROM words")
    suspend fun getAllWords(): List<Word>

    @Query("DELETE FROM words WHERE id = :id")
    suspend fun deleteWordById(id: Int)
}
