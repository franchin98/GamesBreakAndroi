package com.example.gamesbreak.services

import com.example.gamesbreak.data.User
import com.example.gamesbreak.repositories.UserRepository

class LoginService {

    companion object {
        private val userRepository = UserRepository

        fun validateLogin(userName: String, password: String): User? {
            when (val user = validateUser(userName)) {
                is User -> if (validatePassword(user, password)) return user
            }
            return null
        }

        private fun validateUser(userName: String): User? = userRepository.getByID(userName)
        private fun validatePassword(user: User, password: String): Boolean =
            user.password == password
    }
}

