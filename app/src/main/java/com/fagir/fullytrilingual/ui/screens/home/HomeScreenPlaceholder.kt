package com.fagir.fullytrilingual.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable

@Composable
fun HomeScreenPlaceholder(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("es") }

    val languages = listOf("es" to "Español", "en" to "English", "pt" to "Português")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Dropdown trigger
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(8.dp)
        ) {
            Text(
                text = languages.firstOrNull { it.first == selectedLanguage }?.second ?: "Seleccione un idioma",
                modifier = Modifier.padding(8.dp)
            )
        }

        // Dropdown content
        if (expanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                languages.forEach { language ->
                    BasicText(
                        text = language.second,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedLanguage = language.first
                                expanded = false
                            }
                            .padding(8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("addWord/$selectedLanguage")
        }) {
            Text(text = "Go to Add Word Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("wordList")
        }) {
            Text(text = "Navigate to Word List Screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPlaceholderPreview() {
    val navController = rememberNavController()
    HomeScreenPlaceholder(navController = navController)
}
