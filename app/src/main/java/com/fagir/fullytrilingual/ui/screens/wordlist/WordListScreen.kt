package com.fagir.fullytrilingual.ui.screens.wordlist

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fagir.fullytrilingual.data.local.entities.Word
import com.fagir.fullytrilingual.data.repository.WordRepository
import com.fagir.fullytrilingual.ui.screens.home.HomeViewModel  // Para saber el idioma
import com.fagir.fullytrilingual.utils.strings.Strings         // Para usar textos traducidos

@Composable
fun WordListScreen(
    // Repositorio para manejar datos de palabras
    repository: WordRepository,

    // Controlador para navegar entre pantallas
    navController: NavHostController
) {
    // ViewModel que maneja la lista de palabras
    val viewModel: WordListViewModel = viewModel(
        factory = WordListViewModelFactory(repository)
    )

    // ViewModel que maneja el idioma que se está usando
    val homeViewModel: HomeViewModel = viewModel()
    val language = homeViewModel.selectedLanguage.collectAsState().value

    // Observamos la lista de palabras y la dibujamos en pantalla
    val wordList = viewModel.wordList.collectAsState(initial = emptyList())

    // Escuchamos el estado de la ruta actual del NavController
    // para recargar la lista cada vez que regresemos a "wordList".
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(currentBackStackEntry) {
        // Si la ruta actual es "wordList", volvemos a cargar
        if (currentBackStackEntry?.destination?.route == "wordList") {
            Log.d("WordListScreen", "Volvimos a WordList, recargamos los datos.")
            viewModel.getAllWords()  // Me aseguré de que sea 'public' en tu ViewModel
        }
    }

    // Lista perezosa que va mostrando cada WordListItem
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(wordList.value) { word ->
            WordListItem(
                word = word,
                language = language,
                onEdit = {
                    Log.d("WordListScreen", "Editando palabra con id: ${word.id}")
                    navController.navigate("editWord/${word.id}")
                },
                onDelete = {
                    Log.d("WordListScreen", "Eliminando palabra con id: ${word.id}")
                    viewModel.deleteWord(word.id)
                }
            )
        }
    }
}

/**
 * Presenta la información de cada Word y ofrece íconos para editar o eliminar.
 * Usa el idioma para mostrar etiquetas en ES/EN/PT.
 */
@Composable
fun WordListItem(
    // La palabra a mostrar
    word: Word,

    // Idioma actual para escoger las etiquetas
    language: String,

    // Acción al pulsar el botón de editar
    onEdit: () -> Unit,

    // Acción al pulsar el botón de eliminar
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            // Zona donde mostramos la info de la palabra
            Column(
                modifier = Modifier.weight(1f)
            ) {
                //Text(text = "ID: ${word.id}")

                // Etiquetas y valores de las palabras
                Text(text = "${Strings.wordEsLabel[language] ?: "Palabra (ES)"}: ${word.wordEs}")
                Text(text = "${Strings.wordEnLabel[language] ?: "Palabra (EN)"}: ${word.wordEn}")
                Text(text = "${Strings.wordPtLabel[language] ?: "Palabra (PT)"}: ${word.wordPt}")

                Spacer(modifier = Modifier.height(8.dp))

                // Etiquetas y valores de las frases
                Text(text = "${Strings.phraseEsLabel[language] ?: "Frase (ES)"}: ${word.phraseEs}")
                Text(text = "${Strings.phraseEnLabel[language] ?: "Frase (EN)"}: ${word.phraseEn}")
                Text(text = "${Strings.phrasePtLabel[language] ?: "Frase (PT)"}: ${word.phrasePt}")
            }

            // Fila con botones de editar y eliminar
            Row {
                // Botón para editar
                IconButton(onClick = onEdit) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = Strings.editWordTitle[language] ?: "Editar palabra"
                    )
                }

                // Botón para eliminar
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = Strings.buttonSave[language] ?: "Eliminar palabra"
                    )
                }
            }
        }
    }
}
