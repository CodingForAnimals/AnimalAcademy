package com.codingforanimals.animalacademy.model.domain.interactor.news

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.codingforanimals.animalacademy.model.data.models.learning.LearningElement
import com.codingforanimals.animalacademy.model.data.repositories.news.NewsRepositoryImpl

class NewsPagerInteractor {

    val repository = NewsRepositoryImpl()

    suspend fun getNewVideos(): MutableList<LearningElement> {
        return repository.getNewVideos() // TODO IF STATEMENT
    }

    suspend fun getNewArticles(): MutableList<LearningElement> {
        return repository.getNewArticles() // TODO IF STATEMENT
    }

    fun getElementCategory(path: String): Task<DocumentSnapshot> {
        return repository.getCategory(path)  // TODO IF STATEMENT
    }

}