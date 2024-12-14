package com.fagir.fullytrilingual.ui.screens.addword

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.material3.OutlinedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWordScreenPlaceholder() {
    var word = TextFieldValue("")
    var phrase = TextFieldValue("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add Word Placeholder")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = word,
            onValueChange = { word = it },
            label = { Text(text = "Word") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phrase,
            onValueChange = { phrase = it },
            label = { Text(text = "Phrase") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Placeholder action
        }) {
            Text(text = "Save Word")
        }
    }
}
