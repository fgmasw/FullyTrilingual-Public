package com.fagir.fullytrilingual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fagir.fullytrilingual.ui.screens.home.HomeScreenPlaceholder
import com.fagir.fullytrilingual.ui.screens.addword.AddWordScreenPlaceholder
import com.fagir.fullytrilingual.ui.screens.wordlist.WordListScreenPlaceholder
import com.fagir.fullytrilingual.ui.theme.FullyTrilingualTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FullyTrilingualTheme {
                AppScaffold()
            }
        }
    }
}

@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") { HomeScreenPlaceholder(navController) }
            composable("addWord") { AddWordScreenPlaceholder(navController) }
            composable("wordList") { WordListScreenPlaceholder(navController) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppScaffoldPreview() {
    FullyTrilingualTheme {
        AppScaffold()
    }
}
