package com.fagir.fullytrilingual.ui.screens.addword

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.fagir.fullytrilingual.data.local.database.AppDatabase
import com.fagir.fullytrilingual.data.local.entities.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWordScreenPlaceholder(baseLanguage: String) {
    val labels = when (baseLanguage) {
        "es" -> listOf("Palabra", "Traducción 1", "Traducción 2", "Frase (Idioma Base)", "Frase (Traducción 1)", "Frase (Traducción 2)")
        "en" -> listOf("Word", "Translation 1", "Translation 2", "Phrase (Base Language)", "Phrase (Translation 1)", "Phrase (Translation 2)")
        "pt" -> listOf("Palavra", "Tradução 1", "Tradução 2", "Frase (Idioma Base)", "Frase (Tradução 1)", "Frase (Tradução 2)")
        else -> listOf("Word", "Translation 1", "Translation 2", "Phrase (Base Language)", "Phrase (Translation 1)", "Phrase (Translation 2)")
    }

    var word by remember { mutableStateOf("") }
    var translation1 by remember { mutableStateOf("") }
    var translation2 by remember { mutableStateOf("") }
    var phraseBase by remember { mutableStateOf("") }
    var phraseTranslation1 by remember { mutableStateOf("") }
    var phraseTranslation2 by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val wordDao = db.wordDao()

    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add Word Screen (Language: $baseLanguage)")

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = word,
            onValueChange = { word = it },
            label = { Text(text = labels[0]) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = translation1,
            onValueChange = { translation1 = it },
            label = { Text(text = labels[1]) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = translation2,
            onValueChange = { translation2 = it },
            label = { Text(text = labels[2]) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = phraseBase,
            onValueChange = { phraseBase = it },
            label = { Text(text = labels[3]) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = phraseTranslation1,
            onValueChange = { phraseTranslation1 = it },
            label = { Text(text = labels[4]) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = phraseTranslation2,
            onValueChange = { phraseTranslation2 = it },
            label = { Text(text = labels[5]) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                coroutineScope.launch {
                    isLoading = true
                    try {
                        val newWord = Word(
                            baseLanguage = baseLanguage,
                            word = word,
                            translation1 = translation1,
                            translation2 = translation2,
                            phraseBase = phraseBase,
                            phraseTranslation1 = phraseTranslation1,
                            phraseTranslation2 = phraseTranslation2
                        )

                        val insertResult = withContext(Dispatchers.IO) {
                            wordDao.insertWord(newWord)
                        }
                        snackbarHostState.showSnackbar("Word saved! ID: $insertResult")
                        word = ""
                        translation1 = ""
                        translation2 = ""
                        phraseBase = ""
                        phraseTranslation1 = ""
                        phraseTranslation2 = ""
                    } catch (e: Exception) {
                        snackbarHostState.showSnackbar("Error: ${e.message}")
                    } finally {
                        isLoading = false
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text(if (isLoading) "Saving..." else "Save Word")
        }

        SnackbarHost(hostState = snackbarHostState)
    }
}
