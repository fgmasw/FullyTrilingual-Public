package com.fagir.fullytrilingual.data.local.dao

import androidx.room.*

import com.fagir.fullytrilingual.data.local.entities.Word

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word): Long

    // Obtiene todas las palabras que tengan valor en wordEs
    @Query("SELECT * FROM words WHERE wordEs <> ''")
    suspend fun getWordsSpanish(): List<Word>

    // Obtiene todas las palabras que tengan valor en wordEn
    @Query("SELECT * FROM words WHERE wordEn <> ''")
    suspend fun getWordsEnglish(): List<Word>

    // Obtiene todas las palabras que tengan valor en wordPt
    @Query("SELECT * FROM words WHERE wordPt <> ''")
    suspend fun getWordsPortuguese(): List<Word>

    // Retorna absolutamente todas las palabras
    @Query("SELECT * FROM words")
    suspend fun getAllWords(): List<Word>

    // Elimina una palabra por ID
    @Query("DELETE FROM words WHERE id = :id")
    suspend fun deleteWordById(id: Int)

    // Retorna la palabra con el ID especificado (o null si no existe)
    @Query("SELECT * FROM words WHERE id = :id LIMIT 1")
    suspend fun getWordById(id: Int): Word?

    // Actualiza la palabra
    @Update
    suspend fun updateWord(word: Word)
}
