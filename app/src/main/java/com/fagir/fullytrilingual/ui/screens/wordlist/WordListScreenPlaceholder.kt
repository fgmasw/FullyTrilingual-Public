package com.fagir.fullytrilingual.ui.screens.wordlist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordListScreenPlaceholder(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Word List Screen") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("This is the Word List Screen Placeholder.")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Back to Home Screen")
            }
        }
    }
}
