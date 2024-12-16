package com.fagir.fullytrilingual.ui.screens.addword

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fagir.fullytrilingual.data.local.entities.Word
import com.fagir.fullytrilingual.data.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel for adding new words to the database.
 * Handles the logic for inserting a new word using the WordRepository.
 */
class AddWordViewModel(private val repository: WordRepository) : ViewModel() {

    /**
     * Inserts a new word into the database.
     *
     * @param baseLanguage The base language of the word (e.g., "es", "en", "pt").
     * @param word The word in the base language.
     * @param translation1 Translation of the word in the first target language.
     * @param translation2 Translation of the word in the second target language.
     * @param phraseBase A phrase using the word in the base language.
     * @param phraseTranslation1 Translation of the phrase in the first target language.
     * @param phraseTranslation2 Translation of the phrase in the second target language.
     */
    fun insertWord(
        baseLanguage: String,
        word: String,
        translation1: String,
        translation2: String,
        phraseBase: String,
        phraseTranslation1: String,
        phraseTranslation2: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Log input parameters
                Log.d(
                    "AddWordViewModel", "Inserting word: baseLanguage=$baseLanguage, " +
                            "word=$word, translation1=$translation1, translation2=$translation2, " +
                            "phraseBase=$phraseBase, phraseTranslation1=$phraseTranslation1, " +
                            "phraseTranslation2=$phraseTranslation2"
                )

                // Input validation
                if (word.isBlank() || translation1.isBlank() || translation2.isBlank()) {
                    throw IllegalArgumentException("Word and translations must not be empty.")
                }

                // Create Word entity
                val newWord = Word(
                    baseLanguage = baseLanguage,
                    word = word,
                    translation1 = translation1,
                    translation2 = translation2,
                    phraseBase = phraseBase,
                    phraseTranslation1 = phraseTranslation1,
                    phraseTranslation2 = phraseTranslation2
                )

                // Insert word into repository
                val result = repository.insertWord(newWord)
                Log.d("AddWordViewModel", "Word inserted successfully with ID: $result")

            } catch (e: IllegalArgumentException) {
                Log.e("AddWordViewModel", "Input validation failed: ${e.message}")
            } catch (e: Exception) {
                Log.e("AddWordViewModel", "Error inserting word", e)
            }
        }
    }
}
