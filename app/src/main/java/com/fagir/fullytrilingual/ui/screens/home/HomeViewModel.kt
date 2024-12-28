package com.fagir.fullytrilingual.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel que maneja el idioma seleccionado para la pantalla Home.
 */
class HomeViewModel : ViewModel() {

    // Estado del idioma, por defecto "es" (español).
    private val _selectedLanguage = MutableStateFlow("es")
    val selectedLanguage: StateFlow<String> = _selectedLanguage

    init {
        Log.d("HomeViewModel", "Inicializado con idioma: ${_selectedLanguage.value}")
    }

    /**
     * Cambia el idioma y notifica a todas las pantallas
     * que estén observando esta variable.
     */
    fun updateSelectedLanguage(language: String) {
        Log.d("HomeViewModel", "Cambiando de ${_selectedLanguage.value} a $language")
        _selectedLanguage.value = language
    }
}
