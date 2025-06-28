package com.cal.recipes.models.ui

import com.cal.recipes.models.responses.RecipeItem

data class RecipeListUIState(
    val list: List<RecipeItem> = emptyList(),
    var isLoading: Boolean = false,
    var error: Throwable? = null
)