@file:OptIn(ExperimentalMaterial3Api::class) // Opt-In aplicado a todo el archivo

package com.fagir.fullytrilingual.ui.screens.addword

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import com.fagir.fullytrilingual.data.local.database.AppDatabase
import com.fagir.fullytrilingual.data.local.entities.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AddWordScreenPlaceholder(navController: NavController) {
    // Contexto necesario para obtener la base de datos
    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)
    val wordDao = database.wordDao()

    // Estados para capturar datos de entrada
    var spanishWord by remember { mutableStateOf("") }
    var englishWord by remember { mutableStateOf("") }
    var portugueseWord by remember { mutableStateOf("") }
    var spanishPhrase by remember { mutableStateOf("") }
    var englishPhrase by remember { mutableStateOf("") }
    var portuguesePhrase by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add Word Screen") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Campos de texto para ingresar palabras y frases
            OutlinedTextField(
                value = spanishWord,
                onValueChange = { spanishWord = it },
                label = { Text("Palabra en Español") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = englishWord,
                onValueChange = { englishWord = it },
                label = { Text("Word in English") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = portugueseWord,
                onValueChange = { portugueseWord = it },
                label = { Text("Palavra em Português") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = spanishPhrase,
                onValueChange = { spanishPhrase = it },
                label = { Text("Frase en Español") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = englishPhrase,
                onValueChange = { englishPhrase = it },
                label = { Text("Phrase in English") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = portuguesePhrase,
                onValueChange = { portuguesePhrase = it },
                label = { Text("Frase em Português") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para guardar datos en la base de datos
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        val word = Word(
                            spanishWord = spanishWord,
                            englishWord = englishWord,
                            portugueseWord = portugueseWord,
                            spanishPhrase = spanishPhrase,
                            englishPhrase = englishPhrase,
                            portuguesePhrase = portuguesePhrase
                        )
                        wordDao.insertWord(word) // Inserta el dato en la base de datos
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Guardar Palabra")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botón para navegar a la lista de palabras
            Button(
                onClick = {
                    navController.navigate("wordList")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ir a Lista de Palabras")
            }
        }
    }
}
