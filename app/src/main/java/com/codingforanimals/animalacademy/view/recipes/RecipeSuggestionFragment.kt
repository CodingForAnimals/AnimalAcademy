package com.codingforanimals.animalacademy.view.recipes

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.codingforanimals.animalacademy.R
import com.codingforanimals.animalacademy.contract.main.MainContract
import com.codingforanimals.animalacademy.contract.recipes.suggestion.RecipeSuggestionContract
import com.codingforanimals.animalacademy.model.data.models.recipes.SingleRecipe
import com.codingforanimals.animalacademy.model.domain.interactor.main.dialogs.RecipeSuggestionInteractor
import com.codingforanimals.animalacademy.presenter.recipes.suggestion.adapter.ingredients.IngredientsAdapter
import com.codingforanimals.animalacademy.presenter.recipes.suggestion.adapter.steps.StepsAdapter
import com.codingforanimals.animalacademy.presenter.recipes.suggestion.RecipeSuggestionPresenter
import com.codingforanimals.animalacademy.helpers.utils.Utils
import kotlinx.android.synthetic.main.recipe_suggestion.*

class RecipeSuggestionFragment : Fragment(), RecipeSuggestionContract.View {

    private var presenter: RecipeSuggestionPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recipe_suggestion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter?.buildIngredientsRecyclerView()
        presenter?.buildStepsRecyclerView()
        presenter?.buildSpinner()

        add_ingredient_btn.setOnTouchListener(Utils.getResizerOnTouchListener(add_ingredient_btn))
        add_ingredient_btn.setOnClickListener { presenter?.addIngredient() }

        add_step_btn.setOnTouchListener(Utils.getResizerOnTouchListener(add_step_btn))
        add_step_btn.setOnClickListener { presenter?.addStep() }

        src.setOnClickListener { presenter?.getImage() }

        suggest_recipe_btn.setOnTouchListener(Utils.getResizerOnTouchListener(suggest_recipe_btn))
        suggest_recipe_btn.setOnClickListener {
            val recipe = SingleRecipe(
                title.editableText.toString().trim(),
                recipe_desc.editableText.toString().trim()
            )
            presenter?.suggestRecipe(recipe)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainContract.View) presenter =
            RecipeSuggestionPresenter(context, this, context, this, RecipeSuggestionInteractor())
    }

    override fun buildIngredientsRecyclerView(adapter: IngredientsAdapter) {
        ingredients_rv.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
    }

    override fun getIngredientAtPosition(position: Int): View? =
        ingredients_rv.getChildAt(position)


    override fun buildStepsRecyclerView(adapter: StepsAdapter) {
        steps_rv.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter?.onPictureTaken(requestCode, resultCode, data)

    }

    override fun getStepAtPosition(position: Int): View? = steps_rv.getChildAt(position)

    override fun setRecipeImageBitmap(bitmap: Bitmap) {
        src.setImageBitmap(bitmap)
    }

    override fun getRecipeImage(): Bitmap = src.drawable.toBitmap()

    override fun setRecipeImageUri(uri: Uri) = src.setImageURI(uri)

    override fun buildSpinner(adapter: ArrayAdapter<String>) {
        spinner.adapter = adapter
    }

    override fun setPadding(padding: Int) {
        src.setPadding(padding)
    }

    override fun getRecipeTypesSpinnerSelectedItem(): String {
        return spinner.selectedItem.toString()
    }

    override fun makeButtonUnclickable() {
        suggest_recipe_btn.isClickable = false
        suggest_recipe_btn.isFocusable = false
    }

    override fun recipeSentConfirmation() {
        TransitionManager.beginDelayedTransition(recipe_suggestion_root)
        suggest_recipe_btn.visibility = View.INVISIBLE
        recipe_sent_confirmation.visibility = View.VISIBLE
    }

}