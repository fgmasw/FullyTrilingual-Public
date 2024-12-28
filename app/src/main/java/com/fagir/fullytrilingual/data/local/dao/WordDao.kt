package com.fagir.fullytrilingual.data.local.dao

import androidx.room.*
import com.fagir.fullytrilingual.data.local.entities.Word

@Dao
interface WordDao {

    // Inserta una palabra en la base de datos.
    // Usamos REPLACE para sobrescribir si hay conflicto en el ID.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word): Long

    // Devuelve todas las palabras que tengan texto en wordEs.
    // Sirve para filtrar las que tienen contenido en español.
    @Query("SELECT * FROM words WHERE wordEs <> ''")
    suspend fun getWordsSpanish(): List<Word>

    // Devuelve todas las palabras que tengan texto en wordEn.
    // Ayuda a filtrar las que tienen contenido en inglés.
    @Query("SELECT * FROM words WHERE wordEn <> ''")
    suspend fun getWordsEnglish(): List<Word>

    // Devuelve todas las palabras que tengan texto en wordPt.
    // Filtra las que tienen contenido en portugués.
    @Query("SELECT * FROM words WHERE wordPt <> ''")
    suspend fun getWordsPortuguese(): List<Word>

    // Retorna todas las palabras sin filtrar.
    // Aquí obtenemos la lista completa.
    @Query("SELECT * FROM words")
    suspend fun getAllWords(): List<Word>

    // Elimina una palabra según su ID.
    // Esto nos ayuda a borrar elementos específicos.
    @Query("DELETE FROM words WHERE id = :id")
    suspend fun deleteWordById(id: Int)

    // Devuelve la palabra con el ID dado, o null si no existe.
    // Útil para buscar un elemento específico.
    @Query("SELECT * FROM words WHERE id = :id LIMIT 1")
    suspend fun getWordById(id: Int): Word?

    // Actualiza una palabra existente en la base de datos.
    // Usa la clave primaria para identificarla.
    @Update
    suspend fun updateWord(word: Word)
}
