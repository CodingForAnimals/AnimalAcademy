package com.codingforanimals.animalacademy.model.domain.interactor.main.dialogs

import com.google.android.gms.tasks.Task
import com.codingforanimals.animalacademy.model.data.models.recipes.SingleRecipe
import com.codingforanimals.animalacademy.model.data.repositories.recipes.RecipesRepository

class RecipeSuggestionInteractor {

    private val repository = RecipesRepository()

    fun suggestRecipe(recipe: SingleRecipe): Task<Void> {
        val docRef = repository.getSuggestionQuery(recipe.type).document()
        recipe.id = docRef.id
        return docRef.set(recipe)
    }
}