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
import com.fagir.fullytrilingual.utils.strings.Strings  // <-- Importar tu archivo de traducciones

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel()
) {
    // Idioma seleccionado, observado desde el ViewModel
    val language = homeViewModel.selectedLanguage.collectAsState().value

    // Lista de opciones de idioma (código, etiqueta)
    val languages = listOf(
        "es" to "Español",
        "en" to "Inglés",
        "pt" to "Portugués"
    )

    // Controla la expansión del menú desplegable
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Título tomado de tu diccionario (selectLanguageHint)
        Text(
            text = Strings.selectLanguageHint[language] ?: "Selecciona un idioma",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // ExposedDropdownMenuBox para mostrar el menú de idiomas
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            // Campo de texto con el idioma seleccionado
            TextField(
                value = languages.firstOrNull { it.first == language }?.second
                    ?: (Strings.selectLanguageHint[language] ?: "Selecciona un idioma"),
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(0.8f),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Gray,
                    unfocusedIndicatorColor = Color.Gray
                )
            )

            // Desplegable de opciones
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                languages.forEach { (code, label) ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
                            )
                        },
                        onClick = {
                            homeViewModel.updateSelectedLanguage(code)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(170.dp))

        // Botón "Ir a Agregar Palabra"
        Button(
            onClick = { navController.navigate("addWord/$language") },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = Strings.goToAddWord[language] ?: "Ir a Agregar Palabra",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }

        // Botón "Ir a Lista de Palabras"
        Button(
            onClick = { navController.navigate("wordList") },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = Strings.goToWordList[language] ?: "Ir a Lista de Palabras",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
