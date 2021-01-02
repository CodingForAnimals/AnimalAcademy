package com.codingforanimals.animalacademy.model.data.repositories.learning.categories

import com.codingforanimals.animalacademy.model.data.models.learning.Category

interface CategoriesRepository {

    suspend fun getCategories(path: String): MutableList<Category>
}