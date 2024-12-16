package com.fagir.fullytrilingual.ui.screens.wordlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fagir.fullytrilingual.data.repository.WordRepository

class WordListViewModelFactory(
    private val repository: WordRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(WordListViewModel::class.java)) {
            Log.d("WordListViewModelFactory", "Creating instance of WordListViewModel")
            @Suppress("UNCHECKED_CAST")
            WordListViewModel(repository) as T
        } else {
            val errorMessage = "ViewModel not found: Expected WordListViewModel, got ${modelClass.simpleName}"
            Log.e("WordListViewModelFactory", errorMessage)
            throw IllegalArgumentException(errorMessage)
        }
    }
}
