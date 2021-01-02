package com.codingforanimals.animalacademy.contract.recipes.details

import android.graphics.Bitmap
import com.codingforanimals.animalacademy.model.data.models.recipes.SingleRecipe
import com.codingforanimals.animalacademy.presenter.recipes.details.adapter.DetailsIngredientsAdapter
import com.codingforanimals.animalacademy.presenter.recipes.details.adapter.DetailsStepsAdapter

interface RecipeDetailsContract {

    interface View {
        fun setIngredientsRecyclerViewAdapter(adapter: DetailsIngredientsAdapter)
        fun setStepsRecyclerViewAdapter(adapter: DetailsStepsAdapter)
        fun bindRecipe(recipe: SingleRecipe, src: Bitmap)
        fun startPostponedTransition(recipeId: String)
    }

    interface Actions {
        fun buildRecyclerViewsAndBindRecipeInfo(recipe: SingleRecipe, src: Bitmap)
    }

}