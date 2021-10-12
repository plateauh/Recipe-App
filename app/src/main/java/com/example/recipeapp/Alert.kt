package com.example.recipeapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class Alert (private val context: Context, private val recipeItem: RecipeItem) {
    init {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setMessage("${recipeItem.ingredients}\n${recipeItem.instructions}")
        dialogBuilder.setPositiveButton("Got it") { dialog, _ ->
            dialog.cancel()
        }

        val alert = dialogBuilder.create()
        alert.setTitle(recipeItem.title)
        alert.show()
    }
}