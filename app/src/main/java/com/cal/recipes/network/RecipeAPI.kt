package com.cal.recipes.network

import com.cal.recipes.models.responses.RecipeItem
import retrofit2.http.GET

interface RecipeApi {

    @GET("android-test/recipes.json")
    suspend fun getRecipes(): List<RecipeItem>
}