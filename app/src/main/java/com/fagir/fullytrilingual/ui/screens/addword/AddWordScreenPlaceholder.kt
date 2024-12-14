@file:OptIn(ExperimentalMaterial3Api::class) // Opt-In aplicado a todo el archivo

package com.fagir.fullytrilingual.ui.screens.addword

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddWordScreenPlaceholder(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add Word Screen") }
            )
        }
    ) { innerPadding ->
        // Cuerpo de la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Texto de placeholder
            Text(text = "This is the Add Word Screen Placeholder.")

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para navegar a otra pantalla (ejemplo: Word List Screen)
            Button(onClick = {
                navController.navigate("wordList")
            }) {
                Text(text = "Go to Word List Screen")
            }
        }
    }
}
