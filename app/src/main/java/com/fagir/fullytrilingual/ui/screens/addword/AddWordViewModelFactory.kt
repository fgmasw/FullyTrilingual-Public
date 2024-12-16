package com.fagir.fullytrilingual.ui.screens.addword

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fagir.fullytrilingual.data.repository.WordRepository

class AddWordViewModelFactory(
    private val repository: WordRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AddWordViewModel::class.java)) {
            Log.d("AddWordViewModelFactory", "Creating instance of AddWordViewModel")
            @Suppress("UNCHECKED_CAST")
            AddWordViewModel(repository) as T
        } else {
            val errorMessage = "ViewModel not found: Expected AddWordViewModel, got ${modelClass.simpleName}"
            Log.e("AddWordViewModelFactory", errorMessage)
            throw IllegalArgumentException(errorMessage)
        }
    }
}
