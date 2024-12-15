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
fun AddWordScreenPlaceholder() {
    var word by remember { mutableStateOf("") }
    var phrase by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val wordDao = db.wordDao()

    // Estado para manejar el indicador de carga
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add Word Placeholder")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = word,
            onValueChange = { word = it },
            label = { Text(text = "Word") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phrase,
            onValueChange = { phrase = it },
            label = { Text(text = "Phrase") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    isLoading = true // Mostrar el indicador de carga
                    try {
                        withContext(Dispatchers.IO) {
                            wordDao.insertWord(Word(word = word, phrase = phrase))
                        }
                        withContext(Dispatchers.Main) {
                            snackbarHostState.showSnackbar("Word saved!")
                            word = ""
                            phrase = ""
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            snackbarHostState.showSnackbar("Error saving word!")
                        }
                    } finally {
                        isLoading = false // Ocultar el indicador de carga
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = word.isNotBlank() && phrase.isNotBlank() && !isLoading // Deshabilitar si está vacío o cargando
        ) {
            Text(text = if (isLoading) "Saving..." else "Save Word")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
        }

        SnackbarHost(hostState = snackbarHostState)
    }
}
