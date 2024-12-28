package com.fagir.fullytrilingual.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordInputField(
    // Texto que describe para qué sirve este campo
    label: String,

    // Valor actual que se muestra en el campo
    value: String,

    // Acción cuando el usuario cambia el texto
    onValueChange: (String) -> Unit,

    // Modificador para ajustar el estilo o tamaño, si se necesita
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth()
    )
}
