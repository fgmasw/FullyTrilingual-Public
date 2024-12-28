package com.fagir.fullytrilingual.ui.screens.editword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fagir.fullytrilingual.data.repository.WordRepository

// Fábrica para crear instancias de EditWordViewModel.
// Se asegura de inyectar el repositorio correcto.
class EditWordViewModelFactory(
    private val repository: WordRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditWordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditWordViewModel(repository) as T
        }
        throw IllegalArgumentException("Clase de ViewModel desconocida")
    }
}
