package com.cal.recipes.repositories

import com.cal.recipes.models.responses.RecipeItem
import com.cal.recipes.utils.Result

interface RecipeRepository {
    suspend fun getRecipes(): Result<List<RecipeItem>>
}