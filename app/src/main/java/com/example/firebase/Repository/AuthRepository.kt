package com.example.firebase.Repository

import com.example.firebase.Utils.Resource
import com.google.firebase.auth.AuthResult

interface AuthRepository {
    suspend fun register(email: String, name: String, password: String): Resource<AuthResult>
    suspend fun login(email: String, password: String): Resource<AuthResult>
}