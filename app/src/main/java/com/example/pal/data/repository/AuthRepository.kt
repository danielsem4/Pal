package com.example.pal.data.repository

import com.example.pal.data.models.User
import com.example.pal.util.Resource

interface AuthRepository {

    // general requests for auth
    suspend fun currentUser() : Resource<User>

    suspend fun login(email: String, password: String) : Resource<User>

    suspend fun createUser(userName: String, userEmail: String, userPhone : String
    ,userLoginPass: String): Resource<User>

    suspend fun checkUserStatus(): Boolean

    fun logout()

}