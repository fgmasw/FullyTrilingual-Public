package com.fagir.fullytrilingual.ui.screens.addword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fagir.fullytrilingual.data.repository.WordRepository

class AddWordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddWordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddWordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
