package com.example.gamesbreak.ui.authentication

import androidx.lifecycle.ViewModel
import com.example.gamesbreak.data.User
import com.example.gamesbreak.data.UserCredentials
import com.example.gamesbreak.data.UserPreferences
import com.example.gamesbreak.services.LoginService

class LoginViewModel: ViewModel() {
    fun login(username: String, password: String): User? {
        return LoginService.validateLogin(username,password)
    }
    suspend fun saveUserCredentials(user: User, userPreferences: UserPreferences) {
        val userCredentials = UserCredentials(
            id = user.id,
            nickName = user.nickName,
            name = user.name,
            surname = user.surname,
            createdDate = user.createdDate
        )
        userPreferences.saveUserCredentials(userCredentials)
    }
}