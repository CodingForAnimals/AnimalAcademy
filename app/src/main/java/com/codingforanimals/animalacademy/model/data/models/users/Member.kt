package com.codingforanimals.animalacademy.model.data.models.users

class Member(
    var username: String = "",
    var email: String = "",
    var role: String? = "",
    var isVisible: Boolean = true
)