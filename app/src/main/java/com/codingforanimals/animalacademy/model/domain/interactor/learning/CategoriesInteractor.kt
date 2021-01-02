package com.codingforanimals.animalacademy.model.domain.interactor.learning

import android.util.Log
import com.codingforanimals.animalacademy.model.data.models.learning.Category
import com.codingforanimals.animalacademy.model.data.repositories.learning.categories.CategoriesRepositoryImpl

class CategoriesInteractor {

    private val TAG_ERROR = "ERROR"
    private val MSG_ERROR = "Hubo un error al descargar las categorías"
    private val PATH_VIDEO_CATEGORIES = "learning/videos/cat"
    private val PATH_ARTICLES_CATEGORIES = "learning/art/cat"

    private val repository =
        CategoriesRepositoryImpl()

    suspend fun getVideoCategories(): MutableList<Category> {
        try {
            val list = repository.getCategories(PATH_VIDEO_CATEGORIES)
            if (list.isNotEmpty()) return list
        } catch (e: Exception) {
            Log.d(TAG_ERROR, MSG_ERROR)
        }
        return mutableListOf()
    }

    suspend fun getArticlesCategories(): MutableList<Category> {
        try {
            val list = repository.getCategories(PATH_ARTICLES_CATEGORIES)
            if (list.isNotEmpty()) return list
        } catch (e: Exception) {
            Log.d(TAG_ERROR, MSG_ERROR)
        }
        return mutableListOf()
    }
}