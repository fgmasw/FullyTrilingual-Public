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
import com.fagir.fullytrilingual.ui.screens.home.HomeViewModel  // Aquí tomamos el idioma actual
import com.fagir.fullytrilingual.utils.strings.Strings         // Textos traducidos
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditWordScreen(
    // Controlador de navegación
    navController: NavHostController,

    // ID de la palabra que queremos modificar
    wordId: Int,

    // Repositorio para manejar la base de datos
    repository: WordRepository
) {
    // ViewModel que maneja la lógica de edición
    val editWordViewModel: EditWordViewModel = viewModel(
        factory = EditWordViewModelFactory(repository)
    )

    // ViewModel para saber el idioma elegido por el usuario
    val homeViewModel: HomeViewModel = viewModel()
    val language = homeViewModel.selectedLanguage.collectAsState().value

    // Cuando entramos a esta pantalla, cargamos la palabra de la BD usando su ID
    LaunchedEffect(wordId) {
        editWordViewModel.loadWord(wordId)
    }

    // Observamos el estado actual de la palabra que se editará
    val palabraActual by editWordViewModel.currentWord.collectAsState()

    // Variables que mostrarán los campos para la edición
    var palabraEs by remember { mutableStateOf("") }
    var palabraEn by remember { mutableStateOf("") }
    var palabraPt by remember { mutableStateOf("") }
    var fraseEs by remember { mutableStateOf("") }
    var fraseEn by remember { mutableStateOf("") }
    var frasePt by remember { mutableStateOf("") }

    // Cuando recibimos la palabra, llenamos los campos con sus datos
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

    // Estructura principal de la pantalla
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
            // Título que muestra "Editar palabra" más el ID de la palabra
            Text(
                text = (Strings.editWordTitle[language] ?: "Editar palabra") +
                        " (ID: $wordId)",
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

            // Botón para guardar los cambios
            Button(
                onClick = {
                    // Requerimos que ES, EN y PT tengan texto
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
                            // Mensaje de éxito en la parte inferior
                            snackbarHostState.showSnackbar(
                                Strings.successWordUpdated[language] ?: "Cambios guardados correctamente."
                            )
                        }
                    } else {
                        scope.launch {
                            // Si faltan palabras en los campos
                            snackbarHostState.showSnackbar(
                                Strings.errorFieldsEmpty[language] ?: "Campos obligatorios faltantes."
                            )
                        }
                    }
                },
                enabled = !isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Mostramos "Guardando..." mientras está ocupado,
                // de lo contrario, "Guardar cambios" (o su traducción)
                Text(
                    if (isLoading) "Guardando..."
                    else (Strings.buttonSave[language] ?: "Guardar cambios")
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
