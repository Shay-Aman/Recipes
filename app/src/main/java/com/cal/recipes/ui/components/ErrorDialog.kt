package com.cal.recipes.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ErrorDialog(
    title: String = "Error",
    message: String,
    onDismiss: (() -> Unit)
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        },
        title = {
            Text(title, style = MaterialTheme.typography.titleLarge)
        },
        text = {
            Text(message, style = MaterialTheme.typography.bodyMedium)
        },
        shape = MaterialTheme.shapes.medium
    )
}
