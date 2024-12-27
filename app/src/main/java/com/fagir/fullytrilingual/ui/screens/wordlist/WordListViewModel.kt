package com.fagir.fullytrilingual.ui.screens.wordlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fagir.fullytrilingual.data.local.entities.Word
import com.fagir.fullytrilingual.data.repository.WordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordListViewModel(private val repository: WordRepository) : ViewModel() {

    private val _wordList = MutableStateFlow<List<Word>>(emptyList())
    val wordList: StateFlow<List<Word>> = _wordList

    init {
        Log.d("WordListViewModel", "Initializing WordListViewModel...")
        getAllWords()
    }

    /**
     * Carga todas las palabras desde la base de datos
     * y actualiza el StateFlow para que la UI se recomponga.
     */
    private fun getAllWords() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val fetchedWords = repository.getAllWords()
                _wordList.value = fetchedWords
                Log.d("WordListViewModel", "Loaded ${fetchedWords.size} words.")
            } catch (e: Exception) {
                Log.e("WordListViewModel", "Error fetching words", e)
            }
        }
    }

    /**
     * Elimina la palabra con el ID especificado y recarga la lista.
     */
    fun deleteWord(wordId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteWordById(wordId)
                getAllWords()
                Log.d("WordListViewModel", "Deleted word with id: $wordId")
            } catch (e: Exception) {
                Log.e("WordListViewModel", "Error deleting word", e)
            }
        }
    }

    /**
     * (Opcional) Actualiza una palabra existente y recarga la lista.
     * Útil si se decide manejar la edición aquí en lugar de un EditWordViewModel.
     */
    fun updateWord(updatedWord: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.updateWord(updatedWord)
                getAllWords()
                Log.d("WordListViewModel", "Updated word with id: ${updatedWord.id}")
            } catch (e: Exception) {
                Log.e("WordListViewModel", "Error updating word", e)
            }
        }
    }
}
