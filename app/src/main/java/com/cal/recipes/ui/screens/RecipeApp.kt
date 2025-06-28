package com.cal.recipes.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.cal.recipes.models.responses.RecipeItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeApp(
    modifier: Modifier = Modifier,
    viewModel: RecipeListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedRecipe by remember { mutableStateOf<RecipeItem?>(null) }

    if (selectedRecipe == null) {
        RecipeListScreen(
            modifier = modifier,
            uiState = uiState,
            onRecipeTapped = {
                selectedRecipe = it
            }
        )
    } else {
        RecipeDetailScreen(
            modifier = modifier,
            recipe = selectedRecipe!!,
            onBack = { selectedRecipe = null }
        )
    }
}
