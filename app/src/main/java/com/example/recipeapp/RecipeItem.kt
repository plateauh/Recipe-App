package com.example.recipeapp

import com.google.gson.annotations.SerializedName

data class RecipeItem (
    @SerializedName("author")
    val author: String,

    @SerializedName("ingredients")
    val ingredients: String,

    @SerializedName("instructions")
    val instructions: String,

    @SerializedName("title")
    val title: String
)