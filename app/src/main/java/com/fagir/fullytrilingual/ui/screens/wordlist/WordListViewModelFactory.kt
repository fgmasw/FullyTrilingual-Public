package com.fagir.fullytrilingual.ui.screens.wordlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fagir.fullytrilingual.data.repository.WordRepository

class WordListViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
