package com.fagir.fullytrilingual.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _selectedLanguage = MutableStateFlow("es")
    val selectedLanguage: StateFlow<String> = _selectedLanguage

    init {
        Log.d("HomeViewModel", "Initialized with language: ${_selectedLanguage.value}")
    }

    fun updateSelectedLanguage(language: String) {
        Log.d("HomeViewModel", "Updating selected language from ${_selectedLanguage.value} to $language")
        _selectedLanguage.value = language
    }
}
