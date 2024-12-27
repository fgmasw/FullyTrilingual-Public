package com.fagir.fullytrilingual.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel encargado de manejar el idioma seleccionado
 * en la pantalla principal (HomeScreen).
 */
class HomeViewModel : ViewModel() {

    // Almacenamos el idioma seleccionado; por defecto "es".
    private val _selectedLanguage = MutableStateFlow("es")
    val selectedLanguage: StateFlow<String> = _selectedLanguage

    init {
        Log.d("HomeViewModel", "Inicializado con idioma: ${_selectedLanguage.value}")
    }

    /**
     * Actualiza el idioma seleccionado.
     * Todas las pantallas que observen selectedLanguage
     * se recompondrán al cambiar este valor.
     */
    fun updateSelectedLanguage(language: String) {
        Log.d("HomeViewModel", "Cambiando el idioma de ${_selectedLanguage.value} a $language")
        _selectedLanguage.value = language
    }
}
