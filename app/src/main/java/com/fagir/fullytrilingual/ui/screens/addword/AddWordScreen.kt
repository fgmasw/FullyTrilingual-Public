package com.fagir.fullytrilingual.ui.screens.addword

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.fagir.fullytrilingual.data.repository.WordRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWordScreen(
    navController: NavHostController,
    baseLanguage: String,
    repository: WordRepository
) {
    val addWordViewModel: AddWordViewModel = viewModel(factory = AddWordViewModelFactory(repository))

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
    var isLoading by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Agregar Palabra (Idioma Base: ${baseLanguage.uppercase()})",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(value = word, onValueChange = { word = it }, label = { Text(labels[0]) }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = translation1, onValueChange = { translation1 = it }, label = { Text(labels[1]) }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = translation2, onValueChange = { translation2 = it }, label = { Text(labels[2]) }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = phraseBase, onValueChange = { phraseBase = it }, label = { Text(labels[3]) }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = phraseTranslation1, onValueChange = { phraseTranslation1 = it }, label = { Text(labels[4]) }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = phraseTranslation2, onValueChange = { phraseTranslation2 = it }, label = { Text(labels[5]) }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (word.isNotBlank() && translation1.isNotBlank() && translation2.isNotBlank()) {
                        isLoading = true
                        addWordViewModel.insertWord(
                            baseLanguage, word, translation1, translation2,
                            phraseBase, phraseTranslation1, phraseTranslation2
                        )
                        isLoading = false
                        scope.launch {
                            snackbarHostState.showSnackbar("Palabra guardada correctamente.")
                        }
                        word = ""
                        translation1 = ""
                        translation2 = ""
                        phraseBase = ""
                        phraseTranslation1 = ""
                        phraseTranslation2 = ""
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar("Por favor, complete todos los campos obligatorios.")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            ) {
                Text(if (isLoading) "Guardando..." else "Guardar Palabra")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = { navController.popBackStack() }
            ) {
                Text("Volver a la Lista de Palabras")
            }
        }
    }
}
