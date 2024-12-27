package com.fagir.fullytrilingual.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fagir.fullytrilingual.data.local.entities.Word

@Composable
fun WordListItem(
    word: Word,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            // Columna donde se muestran los campos
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "ID: ${word.id}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))

                Text(text = "Word (ES): ${word.wordEs}")
                Text(text = "Word (EN): ${word.wordEn}")
                Text(text = "Word (PT): ${word.wordPt}")
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Phrase (ES): ${word.phraseEs}")
                Text(text = "Phrase (EN): ${word.phraseEn}")
                Text(text = "Phrase (PT): ${word.phrasePt}")
            }

            // Botón para eliminar
            IconButton(
                onClick = onDelete,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Word"
                )
            }
        }
    }
}
