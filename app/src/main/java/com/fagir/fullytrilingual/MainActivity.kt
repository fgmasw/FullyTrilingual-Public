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
import com.fagir.fullytrilingual.ui.screens.home.HomeScreen
import com.fagir.fullytrilingual.ui.screens.wordlist.WordListScreen
import com.fagir.fullytrilingual.ui.theme.FullyTrilingualTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val repository = WordRepository(database.wordDao())

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
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("addWord/{language}") { backStackEntry ->
            val language = backStackEntry.arguments?.getString("language") ?: "es"
            AddWordScreen(navController = navController, baseLanguage = language, repository = repository)
        }
        composable("wordList") {
            WordListScreen(repository = repository)
        }
    }
}
