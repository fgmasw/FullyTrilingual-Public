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

/**
 * ViewModel for managing the word list screen.
 * Handles retrieving and deleting words using the WordRepository.
 */
class WordListViewModel(private val repository: WordRepository) : ViewModel() {

    private val _wordList = MutableStateFlow<List<Word>>(emptyList())
    val wordList: StateFlow<List<Word>> = _wordList

    init {
        Log.d("WordListViewModel", "Initializing WordListViewModel...")
        getAllWords()
    }

    /**
     * Fetches all words from the repository and updates the state.
     */
    private fun getAllWords() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("WordListViewModel", "Fetching all words...")
                _wordList.value = repository.getAllWords()
                Log.d("WordListViewModel", "Words fetched: ${_wordList.value}")
            } catch (e: Exception) {
                Log.e("WordListViewModel", "Error fetching words", e)
            }
        }
    }

    /**
     * Deletes a word by its ID and refreshes the word list.
     *
     * @param wordId ID of the word to delete.
     */
    fun deleteWord(wordId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("WordListViewModel", "Deleting word with ID: $wordId")
                repository.deleteWordById(wordId)
                Log.d("WordListViewModel", "Word with ID: $wordId deleted successfully")
                getAllWords() // Refresh list after deletion
            } catch (e: Exception) {
                Log.e("WordListViewModel", "Error deleting word with ID: $wordId", e)
            }
        }
    }
}
