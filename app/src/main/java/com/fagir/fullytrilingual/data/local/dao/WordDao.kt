package com.fagir.fullytrilingual.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import com.fagir.fullytrilingual.data.local.entities.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word) // Parámetro debe ser de tipo @Entity

    @Query("SELECT * FROM words ORDER BY id DESC")
    fun getAllWords(): Flow<List<Word>> // Retorno debe ser compatible con Room

    @Delete
    suspend fun deleteWord(word: Word) // Parámetro debe ser de tipo @Entity
}
