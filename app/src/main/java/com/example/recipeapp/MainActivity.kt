package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var recipeEditTexts: List<EditText>
lateinit var saveButton: Button
lateinit var viewButton: Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recipeEditTexts = arrayListOf (
            findViewById(R.id.recipe_title_et),
            findViewById(R.id.recipe_author_et),
            findViewById(R.id.recipe_ingredients_et),
            findViewById(R.id.recipe_instructions_et)
        )

        saveButton = findViewById(R.id.save_btn)
        saveButton.setOnClickListener {
            insertRecipe( RecipeItem (
                    recipeEditTexts[0].text.toString(),
                    recipeEditTexts[1].text.toString(),
                    recipeEditTexts[2].text.toString(),
                    recipeEditTexts[3].text.toString() )
            )
            for (property in recipeEditTexts)
                property.setText("")
        }

        viewButton = findViewById(R.id.view_btn)
        viewButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, RecipesActivity::class.java))
            finish()
        }
    }

    private fun insertRecipe(recipe: RecipeItem){
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface!!.addRecipe(recipe)?.enqueue(object: Callback<RecipeItem?>{
            override fun onResponse(call: Call<RecipeItem?>, response: Response<RecipeItem?>) {
                Toast.makeText(this@MainActivity, "Recipe saved!", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<RecipeItem?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                call.cancel()
            }
        })
    }
}