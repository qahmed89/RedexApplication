package com.ahmed.mviapplication.repo

interface LoginRepository {
    fun login(email: String, password: String): Boolean
}