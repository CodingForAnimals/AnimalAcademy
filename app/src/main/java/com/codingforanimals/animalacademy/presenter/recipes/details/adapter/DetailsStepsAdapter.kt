package com.codingforanimals.animalacademy.presenter.recipes.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingforanimals.animalacademy.R
import com.codingforanimals.animalacademy.view.recipes.details.DetailsStepsViewHolder

class DetailsStepsAdapter(val steps: MutableList<String>) :
    RecyclerView.Adapter<DetailsStepsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsStepsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recipe_ingredients_step, parent, false)
        return DetailsStepsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailsStepsViewHolder, position: Int) {
        holder.bindStep(steps[position], position)
    }

    override fun getItemCount(): Int = steps.size
}