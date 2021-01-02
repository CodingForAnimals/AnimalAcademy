package com.codingforanimals.animalacademy.model.domain.interactor.learning

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.codingforanimals.animalacademy.model.data.models.learning.LearningElement
import kotlinx.coroutines.tasks.await

class ElementsInteractor {

    private val TAG_ERROR = "ERROR"
    private val MSG_ERROR = "Hubo un error al descargar los elementos"

    suspend fun getElements(path: String): MutableList<LearningElement> {
        try {
            val list = Firebase.firestore.collection(path).get().await()
                .toObjects(LearningElement::class.java)
            if (list.isNotEmpty()) {
                return list
            }
        } catch (e: Exception) {
            Log.d(TAG_ERROR, MSG_ERROR)
        }
        return mutableListOf()
    }
}