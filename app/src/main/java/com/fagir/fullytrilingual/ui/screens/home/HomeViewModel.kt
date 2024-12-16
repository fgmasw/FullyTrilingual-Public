package com.fagir.fullytrilingual.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel for managing the selected language in the Home Screen.
 */
class HomeViewModel : ViewModel() {

    // Backing property to hold the current selected language
    private val _selectedLanguage = MutableStateFlow("es")

    // Expose an immutable StateFlow for observing the selected language
    val selectedLanguage: StateFlow<String> = _selectedLanguage

    init {
        Log.d("HomeViewModel", "Initialized with language: ${_selectedLanguage.value}")
    }

    /**
     * Updates the currently selected language.
     * @param language The language code to set (e.g., "es", "en", "pt").
     */
    fun updateSelectedLanguage(language: String) {
        Log.d("HomeViewModel", "Updating selected language from ${_selectedLanguage.value} to $language")
        _selectedLanguage.value = language
        Log.d("HomeViewModel", "Language updated successfully to: ${_selectedLanguage.value}")
    }
}
