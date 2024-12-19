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

    fun deleteWord(wordId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteWordById(wordId)
                getAllWords()
            } catch (e: Exception) {
                Log.e("WordListViewModel", "Error deleting word", e)
            }
        }
    }
}
