package com.codingforanimals.animalacademy.presenter.recipes.recipes.recipes

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.codingforanimals.animalacademy.R
import com.codingforanimals.animalacademy.contract.main.MainContract
import com.codingforanimals.animalacademy.contract.recipes.recipes.RecipesContract
import com.codingforanimals.animalacademy.model.data.models.recipes.SingleRecipe
import com.codingforanimals.animalacademy.model.domain.interactor.recipes.toprecipes.RecipesInteractor
import com.codingforanimals.animalacademy.presenter.recipes.recipes.parent.adapter.ParentRecipesAdapter
import com.codingforanimals.animalacademy.presenter.recipes.recipes.parent.adapter.ScrollStateHolder

class RecipesPresenter(
    private val iView: RecipesContract.View,
    private val iMainView: MainContract.View,
    private val interactor: RecipesInteractor
) : RecipesContract.Actions {

    var parentAdapter: ParentRecipesAdapter? = null

    override suspend fun buildRVs(scrollStateHolder: ScrollStateHolder) {
        iMainView.showProgress()
        if (parentAdapter == null) {
            val allRecipeTypes = interactor.getAllRecipeTypes()
            allRecipeTypes?.let { types ->
                parentAdapter = ParentRecipesAdapter(iView, types, scrollStateHolder)
            }
        }
        iView.buildRecipesParentRV(parentAdapter!!)
        iMainView.hideProgress()
    }

    override fun openRecipeDetails(recipe: SingleRecipe, bitmap: Bitmap?, view: View) {

        // send model and bitmap as bundle to details fragment
        val bundle = Bundle()
        bundle.putParcelable("recipe", recipe)
        bundle.putParcelable("src", bitmap)

        // add extras and options to implement animations
        val extras = FragmentNavigatorExtras(view to recipe.id)
        val options =
            NavOptions.Builder().setEnterAnim(R.anim.fragment_in_alpha)
                .setExitAnim(R.anim.fragment_out_slide_alpha)
                .build()

        iMainView.navigateWithOptions(R.id.navigation_recipe_details, bundle, options, extras)

    }
}