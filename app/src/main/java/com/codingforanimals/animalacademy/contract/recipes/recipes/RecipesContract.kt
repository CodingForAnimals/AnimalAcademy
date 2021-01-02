package com.codingforanimals.animalacademy.contract.recipes.recipes

import android.graphics.Bitmap
import com.codingforanimals.animalacademy.model.data.models.recipes.SingleRecipe
import com.codingforanimals.animalacademy.presenter.recipes.recipes.parent.adapter.ParentRecipesAdapter
import com.codingforanimals.animalacademy.presenter.recipes.recipes.parent.adapter.ScrollStateHolder

interface RecipesContract {

    interface View {

        fun startPostponedEnterTransition()
        fun buildRecipesParentRV(adapter: ParentRecipesAdapter)
        fun openRecipeDetails(recipe: SingleRecipe, bitmap: Bitmap?, view: android.view.View)
        fun makeToast(message: String)

    }

    interface Actions {
        suspend fun buildRVs(scrollStateHolder: ScrollStateHolder)
        fun openRecipeDetails(recipe: SingleRecipe, bitmap: Bitmap?, view: android.view.View)
    }

}