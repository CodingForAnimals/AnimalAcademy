package com.codingforanimals.animalacademy.model.data.repositories.users

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.codingforanimals.animalacademy.contract.profiles.ProfileOrgContract
import com.codingforanimals.animalacademy.model.data.dataholders.UserDataHolder
import com.codingforanimals.animalacademy.model.data.models.users.Org

class OrgRepository : ProfileOrgContract.Data {

    private val firestore = FirebaseFirestore.getInstance()

    override fun updateOrg(org: Org?) : Task<Void> {
        return firestore.collection("users").document(UserDataHolder.currentUser.id).update(mapOf(
            "organization" to org
        ))
    }
}