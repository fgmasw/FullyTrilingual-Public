package com.fagir.fullytrilingual.ui.screens.editword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fagir.fullytrilingual.data.repository.WordRepository

class EditWordViewModelFactory(
    private val repository: WordRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditWordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditWordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
