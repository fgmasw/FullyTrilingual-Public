package com.fagir.fullytrilingual

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fagir.fullytrilingual.data.local.database.AppDatabase
import com.fagir.fullytrilingual.data.repository.WordRepository
import com.fagir.fullytrilingual.ui.screens.addword.AddWordViewModel
import com.fagir.fullytrilingual.ui.screens.addword.AddWordViewModelFactory
import com.fagir.fullytrilingual.ui.screens.home.HomeViewModel
import com.fagir.fullytrilingual.ui.screens.wordlist.WordListViewModel
import com.fagir.fullytrilingual.ui.screens.wordlist.WordListViewModelFactory
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val database = AppDatabase.getDatabase(this)
            val repository = WordRepository(database.wordDao())

            val homeViewModel: HomeViewModel = viewModel()
            val addWordViewModel: AddWordViewModel = viewModel(factory = AddWordViewModelFactory(repository))
            val wordListViewModel: WordListViewModel = viewModel(factory = WordListViewModelFactory(repository))

            Scaffold(
                topBar = {
                    TopAppBar(title = { Text("ViewModel Testing") })
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp)
                ) {
                    // HomeViewModel Test
                    Text("Testing HomeViewModel")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(onClick = {
                            homeViewModel.updateSelectedLanguage("es")
                            Log.d("HomeViewModel", "Language set to ES")
                        }) { Text("Set ES") }
                        Button(onClick = {
                            homeViewModel.updateSelectedLanguage("en")
                            Log.d("HomeViewModel", "Language set to EN")
                        }) { Text("Set EN") }
                        Button(onClick = {
                            homeViewModel.updateSelectedLanguage("pt")
                            Log.d("HomeViewModel", "Language set to PT")
                        }) { Text("Set PT") }
                    }
                    Text("Selected Language: ${homeViewModel.selectedLanguage.collectAsState().value}")

                    Spacer(modifier = Modifier.height(16.dp))

                    // AddWordViewModel Test
                    Text("Testing AddWordViewModel")
                    Button(onClick = {
                        try {
                            addWordViewModel.insertWord(
                                baseLanguage = "es",
                                word = "Hola",
                                translation1 = "Hello",
                                translation2 = "Olá",
                                phraseBase = "Hola, ¿cómo estás?",
                                phraseTranslation1 = "Hello, how are you?",
                                phraseTranslation2 = "Olá, como você está?"
                            )
                            Log.d("AddWordViewModel", "Inserted word successfully: Hola")
                        } catch (e: Exception) {
                            Log.e("AddWordViewModel", "Error inserting word: ${e.message}")
                        }
                    }) {
                        Text("Insert Word")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // WordListViewModel Test
                    Text("Testing WordListViewModel")
                    Button(onClick = {
                        try {
                            wordListViewModel.deleteWord(1)
                            Log.d("WordListViewModel", "Deleted word with ID = 1")
                        } catch (e: Exception) {
                            Log.e("WordListViewModel", "Error deleting word: ${e.message}")
                        }
                    }) { Text("Delete Word with ID = 1") }

                    val wordList by wordListViewModel.wordList.collectAsState()
                    wordList.forEach { word ->
                        Log.d("WordListViewModel", "Word loaded: $word")
                        BasicText("Word: ${word.word} - Base Language: ${word.baseLanguage}")
                    }
                }
            }
        }
    }
}
