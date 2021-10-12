package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var recipesList: Recipes
lateinit var recipeRecyclerView: RecyclerView

class RecipesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)
        recipeRecyclerView = findViewById(R.id.recipe_rv)
        recipeRecyclerView.layoutManager = LinearLayoutManager(this)
        recipesList = Recipes()
        setRecipes()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.add_recipe -> {
                startActivity(Intent(this@RecipesActivity, MainActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setRecipes() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<Recipes?>? = apiInterface!!.getRecipes()
        call?.enqueue(object: Callback<Recipes?>{
            override fun onResponse(call: Call<Recipes?>, response: Response<Recipes?>) {
                recipesList = response.body()!!
                recipeRecyclerView.adapter = RecyclerViewAdapter(this@RecipesActivity, recipesList)
            }
            override fun onFailure(call: Call<Recipes?>, t: Throwable) {
                Toast.makeText(this@RecipesActivity, t.message, Toast.LENGTH_SHORT).show()
                call.cancel()
            }
        })
    }
}