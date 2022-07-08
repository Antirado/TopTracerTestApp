package com.example.toptracertest.login


import com.example.toptracertest.network.data.NetworkResponse
import com.example.toptracertest.network.data.asSuccessfulNetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository {
    // Mocking as if login was a network call
    suspend fun login(username: String, password: String): NetworkResponse<Boolean> {
        return withContext(Dispatchers.IO) {
            when {
                username.isEmpty() || password.isEmpty() -> {
                    NetworkResponse.GeneralError("Please fill password and username field")
                }
                password == "password" -> {
                    true.asSuccessfulNetworkResponse()
                }
                else -> {
                    NetworkResponse.GeneralError("Incorrect Username or Password")
                }
            }
        }
    }
}