package com.cal.recipes.repositories

import com.cal.recipes.models.responses.RecipeItem
import com.cal.recipes.network.RecipeApi
import com.cal.recipes.utils.Result
import java.io.IOException

class RecipeRepositoryImpl(
    private val recipeApi: RecipeApi
): RecipeRepository {

    override suspend fun getRecipes(): Result<List<RecipeItem>> {
        return try {
            Result.Success(recipeApi.getRecipes())
        } catch (e: IOException) {
            Result.Error(e)
        }
    }
}