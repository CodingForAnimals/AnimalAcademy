package com.codingforanimals.animalacademy.model.data.repositories.recipes

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.codingforanimals.animalacademy.model.data.models.recipes.TypesRecipe
import kotlinx.coroutines.tasks.await

class RecipesRepository : IRecipesRepository {

    private val RECIPES_DOCREF = "recipes/recipes/"
    private val USERS_COLLECTION = "users"
    private val LIKED_RECIPES_ID_FIELD = "likedRecipesId"
    private val LIKES_FIELD = "likes"
    private val PATH_SUGGESTION = "recipes/recipes/" //should be recipes/suggestions
    private val FIELD_LIKED_RECIPES_ID = "likedRecipesId"

    val firestore = Firebase.firestore

    override fun getRecipesQuery(type: Any?): Query =
        firestore.document(RECIPES_DOCREF).collection(type.toString()).orderBy("likes", Query.Direction.DESCENDING)

    override fun addLike(recipeId: String, type: String) {
        firestore.document(RECIPES_DOCREF).collection(type).document(recipeId)
            .update(LIKES_FIELD, FieldValue.increment(1))
    }

    override fun substractLike(recipeId: String, type: String) {
        firestore.document(RECIPES_DOCREF).collection(type).document(recipeId)
            .update(LIKES_FIELD, FieldValue.increment(-1))
    }

    override fun likedRecipesIdPush(userId: String, recipeId: String): Task<Void> {
        return firestore.collection(USERS_COLLECTION).document(userId)
            .update(LIKED_RECIPES_ID_FIELD, FieldValue.arrayUnion(recipeId))
    }

    override fun likedRecipesIdRemove(userId: String, recipeId: String): Task<Void> {
        return firestore.collection(USERS_COLLECTION).document(userId)
            .update(LIKED_RECIPES_ID_FIELD, FieldValue.arrayRemove(recipeId))
    }

    override fun getSuggestionQuery(type: String): CollectionReference {
        return firestore.document(PATH_SUGGESTION).collection(type)
    }

    override fun getSaltyRecipes(): Query {
        return firestore.collection(RECIPES_DOCREF)
    }

    override suspend fun getAllRecipeTypes(): TypesRecipe? {
        return firestore.document(RECIPES_DOCREF).get().await().toObject(TypesRecipe::class.java)
    }

    override fun getFilteredRecipesQueryFromType(keywords: List<String>, type: String): Query {
        return firestore.document(RECIPES_DOCREF).collection(type)
            .whereArrayContainsAny("keywords", keywords)
    }
}