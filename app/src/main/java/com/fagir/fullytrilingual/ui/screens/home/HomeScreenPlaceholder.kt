@file:OptIn(ExperimentalMaterial3Api::class) // Opt-In aplicado a todo el archivo

package com.fagir.fullytrilingual.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.navigation.NavController

@Composable
fun HomeScreenPlaceholder(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Home Screen") }
            )
        }
    ) { innerPadding ->
        // Cuerpo de la pantalla con botones
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Texto de placeholder
            Text(
                text = "This is the Home Screen Placeholder.",
                modifier = Modifier.padding(bottom = 32.dp) // Espaciado debajo del texto
            )

            // Botón para navegar a Add Word Screen
            Button(
                onClick = { navController.navigate("addWord") },
                modifier = Modifier.fillMaxWidth(0.8f) // Botón ancho
            ) {
                Text("Go to Add Word Screen")
            }

            Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre botones

            // Botón para navegar a Word List Screen
            Button(
                onClick = { navController.navigate("wordList") },
                modifier = Modifier.fillMaxWidth(0.8f) // Botón ancho
            ) {
                Text("Go to Word List Screen")
            }
        }
    }
}
