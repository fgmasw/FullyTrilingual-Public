package com.fagir.fullytrilingual.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel()
) {
    val languages = listOf("es" to "Español", "en" to "English", "pt" to "Português")
    val selectedLanguage by homeViewModel.selectedLanguage.collectAsState()

    // Estado para controlar el menú desplegable
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Seleccione un idioma",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Caja que controla el campo y el menú desplegable clásico
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            // Campo de texto que muestra el idioma seleccionado
            TextField(
                value = languages.firstOrNull { it.first == selectedLanguage }?.second ?: "Seleccione un idioma",
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .menuAnchor() // Asegura que el menú se ancle al campo
                    .fillMaxWidth(0.8f),
                trailingIcon = {
                    // Icono que indica que es un campo desplegable
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Gray,
                    unfocusedIndicatorColor = Color.Gray
                )
            )

            // Menú desplegable clásico
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                languages.forEach { language ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = language.second,
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
                            )
                        },
                        onClick = {
                            homeViewModel.updateSelectedLanguage(language.first)
                            expanded = false
                        }
                    )
                }
            }
        }

        // Separador y botones, siempre visibles
        Spacer(modifier = Modifier.height(170.dp))

        Button(
            onClick = { navController.navigate("addWord/${selectedLanguage}") },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Ir a Agregar Palabra", fontSize = 16.sp, textAlign = TextAlign.Center)
        }

        Button(
            onClick = { navController.navigate("wordList") },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Ir a Lista de Palabras", fontSize = 16.sp, textAlign = TextAlign.Center)
        }
    }
}
