package com.codingforanimals.animalacademy.presenter.recipes.details

import android.graphics.Bitmap
import com.codingforanimals.animalacademy.contract.main.MainContract
import com.codingforanimals.animalacademy.contract.recipes.details.RecipeDetailsContract
import com.codingforanimals.animalacademy.model.data.models.recipes.SingleRecipe
import com.codingforanimals.animalacademy.presenter.recipes.details.adapter.DetailsIngredientsAdapter
import com.codingforanimals.animalacademy.presenter.recipes.details.adapter.DetailsStepsAdapter

class RecipeDetailsPresenter(val view: RecipeDetailsContract.View, val iMainView: MainContract.View) : RecipeDetailsContract.Actions {

    override fun buildRecyclerViewsAndBindRecipeInfo(recipe: SingleRecipe, src: Bitmap) {

        view.bindRecipe(recipe, src)

        val ingredientsAdapter = DetailsIngredientsAdapter(recipe.ing)
        view.setIngredientsRecyclerViewAdapter(ingredientsAdapter)

        val stepsAdapter = DetailsStepsAdapter(recipe.steps)
        view.setStepsRecyclerViewAdapter(stepsAdapter)

        view.startPostponedTransition(recipe.id)
    }
}