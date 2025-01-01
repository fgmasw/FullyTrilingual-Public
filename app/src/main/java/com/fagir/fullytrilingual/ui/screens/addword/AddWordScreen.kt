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

/**
 * Pantalla para agregar una nueva palabra a la base de datos.
 *
 * @param navController Controlador para la navegación entre pantallas.
 * @param language Idioma seleccionado (viene de otra pantalla).
 * @param repository Repositorio para interactuar con la base de datos.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWordScreen(
    navController: NavHostController,
    language: String,
    repository: WordRepository
) {
    // ViewModel que maneja la inserción de palabras.
    val addWordViewModel: AddWordViewModel = viewModel(factory = AddWordViewModelFactory(repository))

    // Campos para la palabra en cada idioma.
    var palabraEs by remember { mutableStateOf("") }
    var palabraEn by remember { mutableStateOf("") }
    var palabraPt by remember { mutableStateOf("") }

    // Campos para la frase en cada idioma.
    var fraseEs by remember { mutableStateOf("") }
    var fraseEn by remember { mutableStateOf("") }
    var frasePt by remember { mutableStateOf("") }

    // Control de estado para indicar si estamos guardando (deshabilitar botón).
    var isLoading by remember { mutableStateOf(false) }

    // Para mostrar mensajes tipo SnackBar (éxito o error).
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Estructura de la pantalla usando Scaffold (Material3).
    Scaffold(
        // Lugar donde se mostrarán los SnackBars.
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        // Columna principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título de la pantalla
            Text(
                text = (Strings.addWordTitle[language] ?: "Agregar palabra nueva") +
                        " (Idioma: ${language.uppercase()})",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // ---------------------
            // Campos para la palabra
            // ---------------------

            // Palabra en Español
            OutlinedTextField(
                value = palabraEs,
                onValueChange = { palabraEs = it },
                label = { Text(Strings.wordEsLabel[language] ?: "Palabra (ES)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Palabra en Inglés
            OutlinedTextField(
                value = palabraEn,
                onValueChange = { palabraEn = it },
                label = { Text(Strings.wordEnLabel[language] ?: "Palabra (EN)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Palabra en Portugués
            OutlinedTextField(
                value = palabraPt,
                onValueChange = { palabraPt = it },
                label = { Text(Strings.wordPtLabel[language] ?: "Palabra (PT)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // ---------------------
            // Campos para la frase
            // ---------------------

            // Frase en Español
            OutlinedTextField(
                value = fraseEs,
                onValueChange = { fraseEs = it },
                label = { Text(Strings.phraseEsLabel[language] ?: "Frase (ES)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Frase en Inglés
            OutlinedTextField(
                value = fraseEn,
                onValueChange = { fraseEn = it },
                label = { Text(Strings.phraseEnLabel[language] ?: "Frase (EN)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Frase en Portugués
            OutlinedTextField(
                value = frasePt,
                onValueChange = { frasePt = it },
                label = { Text(Strings.phrasePtLabel[language] ?: "Frase (PT)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // ---------------------
            // Botón para guardar
            // ---------------------
            Button(
                onClick = {
                    // Verificamos que las palabras (ES/EN/PT) estén llenas.
                    if (palabraEs.isNotBlank() && palabraEn.isNotBlank() && palabraPt.isNotBlank()) {
                        isLoading = true

                        // Insertamos la palabra en la BD usando el ViewModel.
                        addWordViewModel.insertWord(
                            wordEs = palabraEs,
                            wordEn = palabraEn,
                            wordPt = palabraPt,
                            phraseEs = fraseEs,
                            phraseEn = fraseEn,
                            phrasePt = frasePt
                        )

                        isLoading = false

                        // Mostramos mensaje de éxito usando SnackBar.
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = Strings.successWordSaved[language]
                                    ?: "Palabra guardada correctamente."
                            )
                        }

                        // Limpiamos los campos tras guardar.
                        palabraEs = ""
                        palabraEn = ""
                        palabraPt = ""
                        fraseEs = ""
                        fraseEn = ""
                        frasePt = ""

                    } else {
                        // Si faltan campos básicos, mostramos error.
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = Strings.errorFieldsEmpty[language]
                                    ?: "Por favor, completa los campos obligatorios."
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading  // Deshabilita el botón si está guardando.
            ) {
                // Texto que indica si está guardando o no.
                Text(
                    if (isLoading) "Guardando..."
                    else (Strings.buttonSave[language] ?: "Guardar")
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ---------------------
            // Botón "Volver a la lista"
            // ---------------------
            TextButton(
                onClick = {
                    // Aquí usamos la ruta exacta que definiste en tu MainActivity.kt: "wordList"
                    navController.navigate("wordList") {
                        // popUpTo("wordList") { inclusive = true }  // (Opción A: forzar nueva instancia)

                        // Opción B (reutiliza la instancia existente, si está en el back stack)
                        popUpTo("wordList") { inclusive = false }
                    }
                }
            ) {
                Text(Strings.buttonBackToList[language] ?: "Volver a la lista")
            }
        }
    }
}
