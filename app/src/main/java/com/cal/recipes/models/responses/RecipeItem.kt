package com.cal.recipes.models.responses

import com.google.gson.annotations.SerializedName

data class RecipeItem(

    @field:SerializedName("difficulty")
    val difficulty: Int? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("fats")
    val fats: String? = null,

    @field:SerializedName("thumb")
    val thumb: String? = null,

    @field:SerializedName("proteins")
    val proteins: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("carbos")
    val carbos: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("calories")
    val calories: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("time")
    val time: String? = null,

    @field:SerializedName("headline")
    val headline: String? = null,

    @field:SerializedName("country")
    val country: String? = null
)