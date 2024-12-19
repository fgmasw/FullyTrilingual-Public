package com.fagir.fullytrilingual.ui.screens.wordlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

@Composable
fun WordListScreenPlaceholder() {
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val wordDao = db.wordDao()

    var wordList by remember { mutableStateOf<List<Word>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    // Cargar palabras desde la base de datos
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            wordList = withContext(Dispatchers.IO) {
                wordDao.getAllWords()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Lista de Palabras",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (wordList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay palabras aún. Agrega nuevas palabras.")
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(wordList) { word ->
                    WordListItem(word)
                }
            }
        }
    }
}

@Composable
fun WordListItem(word: Word) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Palabra: ${word.word}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Traducción 1: ${word.translation1}")
            Text("Traducción 2: ${word.translation2}")
            Spacer(modifier = Modifier.height(4.dp))
            Text("Frase Base: ${word.phraseBase}")
            Text("Frase Traducción 1: ${word.phraseTranslation1}")
            Text("Frase Traducción 2: ${word.phraseTranslation2}")
        }
    }
}
