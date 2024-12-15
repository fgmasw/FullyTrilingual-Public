package com.fagir.fullytrilingual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fagir.fullytrilingual.ui.screens.addword.AddWordScreenPlaceholder
import com.fagir.fullytrilingual.ui.screens.home.HomeScreenPlaceholder
import com.fagir.fullytrilingual.ui.screens.wordlist.WordListScreenPlaceholder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                // Pantalla de inicio
                composable("home") {
                    HomeScreenPlaceholder(navController = navController)
                }

                // Pantalla para agregar palabras
                composable("addWord/{baseLanguage}") { backStackEntry ->
                    val baseLanguage = backStackEntry.arguments?.getString("baseLanguage") ?: "es"
                    AddWordScreenPlaceholder(baseLanguage = baseLanguage)
                }

                // Pantalla para mostrar la lista de palabras
                composable("wordList") {
                    WordListScreenPlaceholder()
                }
            }
        }
    }
}
