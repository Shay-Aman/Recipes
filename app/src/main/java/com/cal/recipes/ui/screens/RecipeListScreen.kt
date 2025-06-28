package com.cal.recipes.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cal.recipes.models.responses.RecipeItem
import com.cal.recipes.models.ui.RecipeListUIState
import com.cal.recipes.ui.components.ErrorDialog
import com.cal.recipes.ui.components.RecipeListItem

@Composable
fun RecipeListScreen(
    modifier: Modifier = Modifier,
    uiState: RecipeListUIState,
    onRecipeTapped: (RecipeItem) -> Unit
) {

    var shouldShowDialog by remember { mutableStateOf(false) }

    when {
        uiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

        uiState.error != null -> {
            shouldShowDialog = true
            Box(
                modifier = modifier.fillMaxSize()
            ) {
                Text(
                    text = "No content",
                    modifier = Modifier
                        .align(Alignment.Center),
                    style = MaterialTheme.typography.headlineLarge,
                )
            }

            if (shouldShowDialog) {
                ErrorDialog(
                    title = "Error",
                    message = uiState.error?.message ?: "Unknown error"
                ) {
                    shouldShowDialog = false
                }
            }
        }

        uiState.list.isNotEmpty() -> {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Recipes",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                )
                LazyColumn(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(uiState.list) {
                        RecipeListItem(
                            recipe = it,
                            onClick = {
                                onRecipeTapped(it)
                            }
                        )
                    }
                }
            }
        }
    }
}