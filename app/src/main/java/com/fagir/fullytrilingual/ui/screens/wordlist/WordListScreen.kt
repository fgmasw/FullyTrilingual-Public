package com.fagir.fullytrilingual.ui.screens.wordlist

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.fagir.fullytrilingual.data.local.entities.Word
import com.fagir.fullytrilingual.data.repository.WordRepository
import com.fagir.fullytrilingual.ui.screens.home.HomeViewModel  // <-- Para obtener el idioma
import com.fagir.fullytrilingual.utils.strings.Strings         // <-- Para textos traducidos

@Composable
fun WordListScreen(
    repository: WordRepository,
    navController: NavHostController
) {
    // ViewModel para la lista
    val viewModel: WordListViewModel = viewModel(
        factory = WordListViewModelFactory(repository)
    )
    // ViewModel para el idioma
    val homeViewModel: HomeViewModel = viewModel()
    val language = homeViewModel.selectedLanguage.collectAsState().value

    val wordList = viewModel.wordList.collectAsState(initial = emptyList())

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
                    // Navega a la pantalla de edición, pasando el ID de la palabra
                    Log.d("WordListScreen", "Editing word with id: ${word.id}")
                    navController.navigate("editWord/${word.id}")
                },
                onDelete = {
                    // Elimina la palabra
                    Log.d("WordListScreen", "Deleting word with id: ${word.id}")
                    viewModel.deleteWord(word.id)
                }
            )
        }
    }
}

/**
 * Muestra los campos de la entidad `Word` y dos IconButtons: Editar y Eliminar.
 * También traduce las etiquetas "Palabra (ES)", "Palabra (EN)", "Palabra (PT)", etc.
 */
@Composable
fun WordListItem(
    word: Word,
    language: String,
    onEdit: () -> Unit,
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
            // Columna con los datos de la palabra
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "ID: ${word.id}")

                // Etiquetas y valores para las palabras
                Text(text = "${Strings.wordEsLabel[language] ?: "Palabra (ES)"}: ${word.wordEs}")
                Text(text = "${Strings.wordEnLabel[language] ?: "Palabra (EN)"}: ${word.wordEn}")
                Text(text = "${Strings.wordPtLabel[language] ?: "Palabra (PT)"}: ${word.wordPt}")

                Spacer(modifier = Modifier.height(8.dp))

                // Etiquetas y valores para las frases
                Text(text = "${Strings.phraseEsLabel[language] ?: "Frase (ES)"}: ${word.phraseEs}")
                Text(text = "${Strings.phraseEnLabel[language] ?: "Frase (EN)"}: ${word.phraseEn}")
                Text(text = "${Strings.phrasePtLabel[language] ?: "Frase (PT)"}: ${word.phrasePt}")
            }

            // Espacio para íconos (Editar, Eliminar)
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
