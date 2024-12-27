package com.fagir.fullytrilingual.ui.screens.addword

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fagir.fullytrilingual.data.local.entities.Word
import com.fagir.fullytrilingual.data.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddWordViewModel(private val repository: WordRepository) : ViewModel() {

    /**
     * Inserta una nueva palabra según la estructura actual:
     * - wordEs, wordEn, wordPt
     * - phraseEs, phraseEn, phrasePt
     *
     * Valida que al menos los campos de palabra no estén vacíos.
     */
    fun insertWord(
        wordEs: String,
        wordEn: String,
        wordPt: String,
        phraseEs: String,
        phraseEn: String,
        phrasePt: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("AddWordViewModel", "Insertando palabra: ES=($wordEs), EN=($wordEn), PT=($wordPt)")

                // Validación mínima: no permitir palabras vacías
                if (wordEs.isBlank() || wordEn.isBlank() || wordPt.isBlank()) {
                    throw IllegalArgumentException("Las palabras en ES/EN/PT no deben estar vacías.")
                }

                // Construimos la nueva entidad con la estructura actual
                val newWord = Word(
                    wordEs = wordEs,
                    wordEn = wordEn,
                    wordPt = wordPt,
                    phraseEs = phraseEs,
                    phraseEn = phraseEn,
                    phrasePt = phrasePt
                )

                // Insertamos en la base de datos
                repository.insertWord(newWord)
                Log.d("AddWordViewModel", "Palabra insertada correctamente.")
            } catch (e: Exception) {
                Log.e("AddWordViewModel", "Error al insertar la palabra", e)
            }
        }
    }
}
