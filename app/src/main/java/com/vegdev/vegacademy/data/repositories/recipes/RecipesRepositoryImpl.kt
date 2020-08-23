package com.vegdev.vegacademy.data.repositories.recipes

import com.google.firebase.firestore.FirebaseFirestore
import com.vegdev.vegacademy.data.models.Recipe
import kotlinx.coroutines.tasks.await

class RecipesRepositoryImpl : RecipesRepository {

    val firestore = FirebaseFirestore.getInstance()

    override suspend fun fetchRecipes(): MutableList<Recipe> {
        return firestore.collection("rec").get().await().toObjects(Recipe::class.java)
    }

}