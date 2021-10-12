package com.example.recipeapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @POST("/recipes/")
    fun addRecipe(@Body data: RecipeItem): Call<RecipeItem?>?

    @GET("/recipes/")
    fun getRecipes(): Call<Recipes?>?
}