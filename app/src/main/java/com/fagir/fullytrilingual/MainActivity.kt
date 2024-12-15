package com.fagir.fullytrilingual

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.fagir.fullytrilingual.data.local.database.AppDatabase
import com.fagir.fullytrilingual.data.local.entities.Word
import com.fagir.fullytrilingual.data.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener la instancia de la base de datos y el repositorio
        val database = AppDatabase.getDatabase(this)
        val wordRepository = WordRepository(database.wordDao())

        // Prueba del repositorio
        runBlocking(Dispatchers.IO) {
            // Insertar una palabra de prueba
            val testWord = Word(
                baseLanguage = "es",
                word = "hola",
                translation1 = "hello",
                translation2 = "olá",
                phraseBase = "Hola, ¿cómo estás?",
                phraseTranslation1 = "Hello, how are you?",
                phraseTranslation2 = "Olá, como você está?"
            )
            val id = wordRepository.insertWord(testWord)
            Log.d("WordRepositoryTest", "Inserted Word ID: $id")

            // Obtener todas las palabras
            val allWords = wordRepository.getAllWords()
            Log.d("WordRepositoryTest", "All Words: $allWords")

            // Obtener palabras por idioma
            val wordsInSpanish = wordRepository.getWordsByLanguage("es")
            Log.d("WordRepositoryTest", "Words in Spanish: $wordsInSpanish")

            // Eliminar una palabra por ID
            if (allWords.isNotEmpty()) {
                val wordToDelete = allWords.first().id
                wordRepository.deleteWordById(wordToDelete)
                Log.d("WordRepositoryTest", "Deleted Word with ID: $wordToDelete")
            }

            // Verificar después de eliminar
            val updatedWords = wordRepository.getAllWords()
            Log.d("WordRepositoryTest", "Words After Deletion: $updatedWords")
        }

        setContent {
            // Contenido vacío solo para que la app compile
        }
    }
}
