package com.fagir.fullytrilingual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fagir.fullytrilingual.data.local.database.AppDatabase
import com.fagir.fullytrilingual.data.repository.WordRepository
import com.fagir.fullytrilingual.ui.screens.addword.AddWordScreen
import com.fagir.fullytrilingual.ui.screens.editword.EditWordScreen
import com.fagir.fullytrilingual.ui.screens.home.HomeScreen
import com.fagir.fullytrilingual.ui.screens.wordlist.WordListScreen
import com.fagir.fullytrilingual.ui.theme.FullyTrilingualTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1) Obtenemos la instancia de la base de datos y el repositorio
        val database = AppDatabase.getDatabase(this)
        val repository = WordRepository(database.wordDao())

        // 2) Iniciamos la interfaz de usuario con Jetpack Compose
        setContent {
            FullyTrilingualTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController, repository = repository)
            }
        }
    }
}

@Composable
fun AppNavHost(
    // Controlador para manejar la navegación
    navController: NavHostController,

    // Repositorio para trabajar con la base de datos
    repository: WordRepository
) {
    NavHost(navController = navController, startDestination = "home") {

        // Pantalla principal o de inicio
        composable("home") {
            // Enviamos el repositorio a HomeScreen para “montar” el WordListViewModel (hack).
            HomeScreen(
                navController = navController,
                repository = repository
            )
        }

        // Pantalla para agregar una palabra, recibiendo el idioma seleccionado
        composable("addWord/{language}") { backStackEntry ->
            val language = backStackEntry.arguments?.getString("language") ?: "es"
            AddWordScreen(
                navController = navController,
                language = language,
                repository = repository
            )
        }

        // Pantalla que muestra la lista de palabras guardadas
        composable("wordList") {
            WordListScreen(
                repository = repository,
                navController = navController
            )
        }

        // Pantalla para editar una palabra específica:
        // se recibe "wordId" seleccionado en WordListScreen
        composable("editWord/{wordId}") { backStackEntry ->
            val wordIdString = backStackEntry.arguments?.getString("wordId") ?: "0"
            val wordId = wordIdString.toInt()
            EditWordScreen(
                navController = navController,
                wordId = wordId,
                repository = repository
            )
        }
    }
}
