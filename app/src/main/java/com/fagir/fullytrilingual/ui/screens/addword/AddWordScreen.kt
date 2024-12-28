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
import com.fagir.fullytrilingual.utils.strings.Strings
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWordScreen(
    // Navegador para movernos entre pantallas
    navController: NavHostController,

    // Idioma que viene de la pantalla anterior
    language: String,

    // Repositorio para interactuar con la base de datos
    repository: WordRepository
) {
    // Creamos nuestro ViewModel con la fábrica que usa el repositorio
    val addWordViewModel: AddWordViewModel = viewModel(factory = AddWordViewModelFactory(repository))

    // Variables para almacenar las palabras y frases en cada idioma
    var palabraEs by remember { mutableStateOf("") }
    var palabraEn by remember { mutableStateOf("") }
    var palabraPt by remember { mutableStateOf("") }
    var fraseEs by remember { mutableStateOf("") }
    var fraseEn by remember { mutableStateOf("") }
    var frasePt by remember { mutableStateOf("") }

    // Control para indicar si estamos guardando y para mostrar mensajes
    var isLoading by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Estructura básica de Material 3 para la pantalla
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        // Columna principal que organiza la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Muestra el título y el idioma que se está usando
            Text(
                text = (Strings.addWordTitle[language] ?: "Agregar palabra nueva") +
                        " (Idioma: ${language.uppercase()})",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Campo para la palabra en español
            OutlinedTextField(
                value = palabraEs,
                onValueChange = { palabraEs = it },
                label = { Text(Strings.wordEsLabel[language] ?: "Palabra (ES)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Campo para la palabra en inglés
            OutlinedTextField(
                value = palabraEn,
                onValueChange = { palabraEn = it },
                label = { Text(Strings.wordEnLabel[language] ?: "Palabra (EN)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Campo para la palabra en portugués
            OutlinedTextField(
                value = palabraPt,
                onValueChange = { palabraPt = it },
                label = { Text(Strings.wordPtLabel[language] ?: "Palabra (PT)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo para la frase en español
            OutlinedTextField(
                value = fraseEs,
                onValueChange = { fraseEs = it },
                label = { Text(Strings.phraseEsLabel[language] ?: "Frase (ES)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Campo para la frase en inglés
            OutlinedTextField(
                value = fraseEn,
                onValueChange = { fraseEn = it },
                label = { Text(Strings.phraseEnLabel[language] ?: "Frase (EN)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Campo para la frase en portugués
            OutlinedTextField(
                value = frasePt,
                onValueChange = { frasePt = it },
                label = { Text(Strings.phrasePtLabel[language] ?: "Frase (PT)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Botón para guardar la nueva palabra en la base de datos
            Button(
                onClick = {
                    // Revisamos que los campos de palabra (ES, EN, PT) no estén vacíos
                    if (palabraEs.isNotBlank() && palabraEn.isNotBlank() && palabraPt.isNotBlank()) {
                        isLoading = true

                        // Insertamos la palabra usando el ViewModel
                        addWordViewModel.insertWord(
                            wordEs = palabraEs,
                            wordEn = palabraEn,
                            wordPt = palabraPt,
                            phraseEs = fraseEs,
                            phraseEn = fraseEn,
                            phrasePt = frasePt
                        )

                        isLoading = false
                        scope.launch {
                            // Mostramos mensaje de éxito, usando la traducción
                            snackbarHostState.showSnackbar(
                                Strings.successWordSaved[language] ?: "Palabra guardada correctamente."
                            )
                        }

                        // Limpiamos los campos después de guardar
                        palabraEs = ""
                        palabraEn = ""
                        palabraPt = ""
                        fraseEs = ""
                        fraseEn = ""
                        frasePt = ""

                    } else {
                        // Si faltan campos, lanzamos un mensaje de error
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                Strings.errorFieldsEmpty[language] ?: "Por favor, completa los campos obligatorios."
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            ) {
                // Si está cargando, mostramos "Guardando...",
                // de lo contrario, tomamos la traducción de "Guardar"
                Text(
                    if (isLoading) "Guardando..."
                    else (Strings.buttonSave[language] ?: "Guardar")
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para regresar a la lista de palabras
            TextButton(onClick = { navController.popBackStack() }) {
                Text(Strings.buttonBackToList[language] ?: "Volver a la lista")
            }
        }
    }
}
