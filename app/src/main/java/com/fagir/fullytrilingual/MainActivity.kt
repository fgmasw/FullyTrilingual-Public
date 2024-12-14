package com.fagir.fullytrilingual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.fagir.fullytrilingual.ui.screens.addword.AddWordScreenPlaceholder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddWordScreenPlaceholder()
        }
    }
}
