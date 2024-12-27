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
import com.fagir.fullytrilingual.utils.strings.Strings  // <-- Importar tu archivo de traducciones
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWordScreen(
    navController: NavHostController,
    language: String,           // Se recibe el idioma seleccionado
    repository: WordRepository
) {
    // Usamos el ViewModel con su Factory, inyectando el repositorio
    val addWordViewModel: AddWordViewModel = viewModel(factory = AddWordViewModelFactory(repository))

    // Estados para cada campo de la entidad Word
    var palabraEs by remember { mutableStateOf("") }
    var palabraEn by remember { mutableStateOf("") }
    var palabraPt by remember { mutableStateOf("") }
    var fraseEs by remember { mutableStateOf("") }
    var fraseEn by remember { mutableStateOf("") }
    var frasePt by remember { mutableStateOf("") }

    // Control de carga y notificaciones
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
            // Título dinámico con el idioma
            Text(
                text = (Strings.addWordTitle[language] ?: "Agregar palabra nueva") +
                        " (Idioma: ${language.uppercase()})",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Campos para la palabra en cada idioma
            OutlinedTextField(
                value = palabraEs,
                onValueChange = { palabraEs = it },
                label = { Text(Strings.wordEsLabel[language] ?: "Palabra (ES)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = palabraEn,
                onValueChange = { palabraEn = it },
                label = { Text(Strings.wordEnLabel[language] ?: "Palabra (EN)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = palabraPt,
                onValueChange = { palabraPt = it },
                label = { Text(Strings.wordPtLabel[language] ?: "Palabra (PT)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campos para la frase de ejemplo en cada idioma
            OutlinedTextField(
                value = fraseEs,
                onValueChange = { fraseEs = it },
                label = { Text(Strings.phraseEsLabel[language] ?: "Frase (ES)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = fraseEn,
                onValueChange = { fraseEn = it },
                label = { Text(Strings.phraseEnLabel[language] ?: "Frase (EN)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = frasePt,
                onValueChange = { frasePt = it },
                label = { Text(Strings.phrasePtLabel[language] ?: "Frase (PT)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Botón para guardar
            Button(
                onClick = {
                    // Validamos que al menos los campos de palabra ES/EN/PT no estén vacíos
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
                            // Texto de éxito en el Snackbar (successWordSaved)
                            snackbarHostState.showSnackbar(
                                Strings.successWordSaved[language] ?: "Palabra guardada correctamente."
                            )
                        }

                        // Limpiamos los campos tras guardar
                        palabraEs = ""
                        palabraEn = ""
                        palabraPt = ""
                        fraseEs = ""
                        fraseEn = ""
                        frasePt = ""

                    } else {
                        scope.launch {
                            // Texto de error en el Snackbar (errorFieldsEmpty)
                            snackbarHostState.showSnackbar(
                                Strings.errorFieldsEmpty[language] ?: "Por favor, completa los campos obligatorios."
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            ) {
                // Si estamos guardando, “Guardando...”; si no, el texto del diccionario (buttonSave)
                Text(
                    if (isLoading) "Guardando..."
                    else (Strings.buttonSave[language] ?: "Guardar")
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para volver (buttonBackToList)
            TextButton(onClick = { navController.popBackStack() }) {
                Text(Strings.buttonBackToList[language] ?: "Volver a la lista")
            }
        }
    }
}
