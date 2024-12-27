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

        // 1) Obtén la instancia de la BD y el repositorio
        val database = AppDatabase.getDatabase(this)
        val repository = WordRepository(database.wordDao())

        // 2) Configura la UI con Jetpack Compose
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
    navController: NavHostController,
    repository: WordRepository
) {
    NavHost(navController = navController, startDestination = "home") {

        // Pantalla de inicio
        composable("home") {
            // Aquí le pasamos el repository para que en HomeScreen
            // se pueda "montar" el WordListViewModel en secreto (hack).
            HomeScreen(
                navController = navController,
                repository = repository
            )
        }

        // Pantalla para agregar una palabra con el idioma seleccionado
        composable("addWord/{language}") { backStackEntry ->
            val language = backStackEntry.arguments?.getString("language") ?: "es"
            AddWordScreen(
                navController = navController,
                language = language,
                repository = repository
            )
        }

        // Pantalla que muestra la lista de palabras almacenadas
        composable("wordList") {
            WordListScreen(
                repository = repository,
                navController = navController
            )
        }

        // Pantalla para editar una palabra específica
        // Se recibe el "wordId" seleccionado en WordListScreen
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
