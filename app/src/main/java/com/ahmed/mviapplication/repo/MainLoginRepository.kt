package com.ahmed.mviapplication.repo

class MainLoginRepository : LoginRepository {
    override fun login(email: String, password: String): Boolean {
        return true
    }
}