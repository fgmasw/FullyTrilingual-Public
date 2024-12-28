package com.fagir.fullytrilingual.ui.screens.wordlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fagir.fullytrilingual.data.local.entities.Word
import com.fagir.fullytrilingual.data.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WordListViewModel(private val repository: WordRepository) : ViewModel() {

    // Flujo que guarda la lista de palabras y avisa a la IU cuando hay cambios
    private val _wordList = MutableStateFlow<List<Word>>(emptyList())
    val wordList: StateFlow<List<Word>> get() = _wordList

    init {
        Log.d("WordListViewModel", "Inicializando WordListViewModel...")
        getAllWords()
    }

    /**
     * Carga todas las palabras desde la BD
     * y actualiza el StateFlow para que la IU se recomponga.
     * Se hace pública para poder llamarla al volver desde otras pantallas.
     */
    fun getAllWords() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val fetchedWords = repository.getAllWords()
                _wordList.value = fetchedWords
                Log.d("WordListViewModel", "Se cargaron ${fetchedWords.size} palabras.")
            } catch (e: Exception) {
                Log.e("WordListViewModel", "Error al obtener las palabras", e)
            }
        }
    }

    /**
     * Elimina la palabra con el ID indicado y recarga la lista.
     */
    fun deleteWord(wordId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteWordById(wordId)
                getAllWords()
                Log.d("WordListViewModel", "Se eliminó la palabra con id: $wordId")
            } catch (e: Exception) {
                Log.e("WordListViewModel", "Error al eliminar la palabra", e)
            }
        }
    }

    /**
     * (Opcional) Actualiza una palabra y recarga la lista.
     * Sería útil si manejamos la edición aquí.
     */
    fun updateWord(updatedWord: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.updateWord(updatedWord)
                getAllWords()
                Log.d("WordListViewModel", "Se actualizó la palabra con id: ${updatedWord.id}")
            } catch (e: Exception) {
                Log.e("WordListViewModel", "Error al actualizar la palabra", e)
            }
        }
    }
}
