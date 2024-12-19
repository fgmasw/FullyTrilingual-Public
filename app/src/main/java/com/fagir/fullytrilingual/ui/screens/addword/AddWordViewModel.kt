package com.fagir.fullytrilingual.ui.screens.addword

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fagir.fullytrilingual.data.local.entities.Word
import com.fagir.fullytrilingual.data.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddWordViewModel(private val repository: WordRepository) : ViewModel() {

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
                Log.d("AddWordViewModel", "Inserting word: $word ($baseLanguage)")
                if (word.isBlank() || translation1.isBlank() || translation2.isBlank()) {
                    throw IllegalArgumentException("Word and translations must not be empty.")
                }

                val newWord = Word(
                    baseLanguage = baseLanguage,
                    word = word,
                    translation1 = translation1,
                    translation2 = translation2,
                    phraseBase = phraseBase,
                    phraseTranslation1 = phraseTranslation1,
                    phraseTranslation2 = phraseTranslation2
                )
                repository.insertWord(newWord)
                Log.d("AddWordViewModel", "Word inserted successfully.")
            } catch (e: Exception) {
                Log.e("AddWordViewModel", "Error inserting word", e)
            }
        }
    }
}
