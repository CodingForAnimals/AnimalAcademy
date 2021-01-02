package com.codingforanimals.animalacademy.presenter.recipes.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingforanimals.animalacademy.R
import com.codingforanimals.animalacademy.model.data.models.recipes.Ingredient
import com.codingforanimals.animalacademy.view.recipes.details.DetailsIngredientsViewHolder

class DetailsIngredientsAdapter(val ingredients: MutableList<Ingredient>) :
    RecyclerView.Adapter<DetailsIngredientsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailsIngredientsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_details_ingredient, parent, false)
        return DetailsIngredientsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailsIngredientsViewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.bindIngredient(ingredient)
    }

    override fun getItemCount(): Int = ingredients.size
}