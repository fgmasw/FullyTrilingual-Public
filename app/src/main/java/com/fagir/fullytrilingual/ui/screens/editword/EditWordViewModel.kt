package com.fagir.fullytrilingual.ui.screens.editword

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fagir.fullytrilingual.data.local.entities.Word
import com.fagir.fullytrilingual.data.repository.WordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditWordViewModel(private val repository: WordRepository) : ViewModel() {

    // Guardamos la palabra actual que estamos editando
    private val _currentWord = MutableStateFlow<Word?>(null)
    val currentWord: StateFlow<Word?> get() = _currentWord

    // Controla si estamos realizando una operación que demora (cargando)
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    // Carga la palabra desde la base de datos usando su ID y la asigna a _currentWord
    fun loadWord(wordId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val word = repository.getWordById(wordId)
                _currentWord.value = word
                _isLoading.value = false
                Log.d("EditWordViewModel", "Word loaded: $word")
            } catch (e: Exception) {
                Log.e("EditWordViewModel", "Error al cargar la palabra", e)
                _isLoading.value = false
            }
        }
    }

    // Actualiza la palabra en la base de datos
    fun updateWord(word: Word) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.updateWord(word)
                _isLoading.value = false
                Log.d("EditWordViewModel", "Word updated: $word")
            } catch (e: Exception) {
                Log.e("EditWordViewModel", "Error al actualizar la palabra", e)
                _isLoading.value = false
            }
        }
    }
}
