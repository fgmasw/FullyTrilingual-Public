package com.fagir.fullytrilingual.ui.screens.editword

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.fagir.fullytrilingual.data.local.entities.Word
import com.fagir.fullytrilingual.data.repository.WordRepository
import com.fagir.fullytrilingual.ui.screens.home.HomeViewModel  // <-- Para leer el idioma
import com.fagir.fullytrilingual.utils.strings.Strings         // <-- Importar tu archivo de traducciones
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditWordScreen(
    navController: NavHostController,
    wordId: Int,                    // ID de la palabra a editar
    repository: WordRepository
) {
    // ViewModel para editar
    val editWordViewModel: EditWordViewModel = viewModel(
        factory = EditWordViewModelFactory(repository)
    )

    // ViewModel para obtener el idioma seleccionado
    val homeViewModel: HomeViewModel = viewModel()
    val language = homeViewModel.selectedLanguage.collectAsState().value

    // Al ingresar a la pantalla, cargamos la palabra desde la BD.
    LaunchedEffect(wordId) {
        editWordViewModel.loadWord(wordId)
    }

    // Observa el estado de la palabra en el ViewModel de edición
    val palabraActual by editWordViewModel.currentWord.collectAsState()

    // Estados de los campos; se llenarán cuando `palabraActual` cambie
    var palabraEs by remember { mutableStateOf("") }
    var palabraEn by remember { mutableStateOf("") }
    var palabraPt by remember { mutableStateOf("") }
    var fraseEs by remember { mutableStateOf("") }
    var fraseEn by remember { mutableStateOf("") }
    var frasePt by remember { mutableStateOf("") }

    // Precarga de datos cuando la palabra se obtiene de la BD
    LaunchedEffect(palabraActual) {
        palabraActual?.let { word ->
            palabraEs = word.wordEs
            palabraEn = word.wordEn
            palabraPt = word.wordPt
            fraseEs = word.phraseEs
            fraseEn = word.phraseEn
            frasePt = word.phrasePt
        }
    }

    val isLoading by editWordViewModel.isLoading.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título dinámico: "Editar palabra" + (ID: x)
            Text(
                text = (Strings.editWordTitle[language] ?: "Editar palabra") +
                        " (ID: $wordId)",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Campos para editar - Usamos las llaves de tu Strings.kt
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

            // Botón para actualizar
            Button(
                onClick = {
                    // Validamos que al menos ES, EN, PT no estén vacíos
                    if (palabraEs.isNotBlank() && palabraEn.isNotBlank() && palabraPt.isNotBlank()) {
                        scope.launch {
                            editWordViewModel.updateWord(
                                Word(
                                    id = wordId,
                                    wordEs = palabraEs,
                                    wordEn = palabraEn,
                                    wordPt = palabraPt,
                                    phraseEs = fraseEs,
                                    phraseEn = fraseEn,
                                    phrasePt = frasePt
                                )
                            )
                            // Mostramos el mensaje de éxito (successWordUpdated)
                            snackbarHostState.showSnackbar(
                                Strings.successWordUpdated[language] ?: "Cambios guardados correctamente."
                            )
                        }
                    } else {
                        scope.launch {
                            // Error si faltan campos obligatorios
                            snackbarHostState.showSnackbar(
                                Strings.errorFieldsEmpty[language] ?: "Campos obligatorios faltantes."
                            )
                        }
                    }
                },
                enabled = !isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Si estamos guardando, "Guardando..."; sino, "Guardar cambios"
                Text(
                    if (isLoading) "Guardando..."
                    else (Strings.buttonSave[language] ?: "Guardar cambios")
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
