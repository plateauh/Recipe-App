package com.example.recipeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.databinding.RecipeItemBinding

class RecyclerViewAdapter(private val context: Context, private val recipes: Recipes):
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: RecipeItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            RecipeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = recipes[position]
        holder.binding.apply {
            recipeTitleTv.text = item.title
            recipeAuthorTv.text = item.author
            recipeCard.setOnClickListener {
                Alert(context, item)
            }
        }
    }

    override fun getItemCount() = recipes.size
}