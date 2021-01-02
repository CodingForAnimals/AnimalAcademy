package com.codingforanimals.animalacademy.model.data.dataholders

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.codingforanimals.animalacademy.model.data.models.users.User
import kotlinx.coroutines.tasks.await

object UserDataHolder {

    lateinit var currentUser: User
    private var isInitialized = false

    suspend fun getUserData(): User {
        if (!isInitialized) {
            isInitialized = true
            currentUser = fetchUser()
        }
        return currentUser
    }

    fun setUser(user: User) {
        isInitialized = true
        currentUser = user
    }

    fun grantUserRole() {
        currentUser.isUser = true
    }

    fun grantOrganizationRole() {
        currentUser.isOrg = true
    }

    fun grantAdminRole() {
        currentUser.isAdmin = true
    }

    fun resetUser() {
        isInitialized = false
        currentUser = User()
    }

    private suspend fun fetchUser(): User {
        FirebaseAuth.getInstance().currentUser?.let {
            val uid = it.uid
            val user =
                FirebaseFirestore.getInstance().collection("users").document(uid).get().await()
                    .toObject(User::class.java)
            user?.let {
                return it
            }
        }
        return User()
    }

    fun onLogIn(user: DocumentSnapshot?) {
        val userData = user?.toObject(User::class.java)
        userData?.let {
            isInitialized = true
            currentUser = userData
        }
    }


}