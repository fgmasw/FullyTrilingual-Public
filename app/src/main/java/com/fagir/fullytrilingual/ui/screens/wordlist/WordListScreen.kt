package com.fagir.fullytrilingual.ui.screens.wordlist

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fagir.fullytrilingual.data.local.entities.Word
import com.fagir.fullytrilingual.data.repository.WordRepository
import com.fagir.fullytrilingual.ui.components.WordListItem

@Composable
fun WordListScreen(repository: WordRepository) {
    val viewModel: WordListViewModel = viewModel(
        factory = WordListViewModelFactory(repository)
    )

    val wordList = viewModel.wordList.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(wordList.value) { word ->
            WordListItem(
                word = word,
                onDelete = {
                    Log.d("WordListScreen", "Deleting word with id: ${word.id}")
                    viewModel.deleteWord(word.id)
                }
            )
        }
    }
}
