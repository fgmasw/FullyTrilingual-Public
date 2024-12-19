package com.fagir.fullytrilingual.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fagir.fullytrilingual.data.local.entities.Word

@Composable
fun WordListItem(word: Word, onDelete: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = word.word)
        TextButton(onClick = onDelete) {
            Text("Delete")
        }
    }
    Divider()
}
