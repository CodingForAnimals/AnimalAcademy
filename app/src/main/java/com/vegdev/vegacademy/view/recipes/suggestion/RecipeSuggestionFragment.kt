package com.vegdev.vegacademy.view.recipes.suggestion

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
import com.vegdev.vegacademy.R
import com.vegdev.vegacademy.model.data.models.SingleRecipe
import com.vegdev.vegacademy.model.domain.interactor.main.dialogs.RecipeSuggestionInteractor
import com.vegdev.vegacademy.presenter.recipes.suggestion.ingredientsAdapter.IngredientsAdapter
import com.vegdev.vegacademy.presenter.recipes.suggestion.stepsAdapter.StepsAdapter
import com.vegdev.vegacademy.presenter.recipes.suggestion.suggestion.RecipeSuggestionPresenter
import com.vegdev.vegacademy.utils.LayoutUtils
import com.vegdev.vegacademy.view.main.main.MainView
import kotlinx.android.synthetic.main.fragment_recipe_suggestion.*

class RecipeSuggestionFragment : Fragment(), RecipeSuggestionView {

    private var presenter: RecipeSuggestionPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_suggestion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter?.buildIngredientsRecyclerView()
        presenter?.buildStepsRecyclerView()
        presenter?.buildSpinner()

        add_ingredient_btn.setOnClickListener { presenter?.addIngredient() }
        add_step_btn.setOnClickListener { presenter?.addStep() }
        src.setOnClickListener { presenter?.getImage() }

        accept.setOnTouchListener(LayoutUtils().getResizerOnTouchListener(accept))
        accept.setOnClickListener {
            val recipe = SingleRecipe(
                title.editableText.toString().trim(),
                recipe_desc.editableText.toString().trim()
            )
            presenter?.suggestRecipe(recipe)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainView) presenter =
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

}