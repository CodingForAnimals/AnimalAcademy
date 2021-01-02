package com.codingforanimals.animalacademy.model.data.repositories.learning.categories

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.codingforanimals.animalacademy.model.data.models.learning.Category
import kotlinx.coroutines.tasks.await

class CategoriesRepositoryImpl :
    CategoriesRepository {

    override suspend fun getCategories(path: String): MutableList<Category> {
        return Firebase.firestore.collection(path).get().await().toObjects(Category::class.java)
    }
}