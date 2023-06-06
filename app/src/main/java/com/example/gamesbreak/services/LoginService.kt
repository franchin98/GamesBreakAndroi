package com.example.gamesbreak.services

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat.startActivity
import com.example.gamesbreak.activities.HomeActivity
import com.example.gamesbreak.data.User
import com.example.gamesbreak.repositories.UserRepository

object LoginService {

    private val userRepository = UserRepository

    fun validateLogin(userName: AppCompatEditText, password: AppCompatEditText) {
        when (val user = validateUser(userName.text.toString())) {
            is User -> validatePassword(user, password)
            else -> if (userName.text.toString().isEmpty() || password.text.toString().isEmpty()) {
                Toast.makeText(
                    userName.context, "Verifique los datos ingresados.", Toast.LENGTH_LONG
                ).show()
            } else Toast.makeText(userName.context, "¡El usuario no existe!", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun validateUser(userName: String): User? = userRepository.getByID(userName)


    private fun validatePassword(user: User, password: AppCompatEditText) {
        if (user.password == password.text.toString()) {
            val intent = Intent(password.context, HomeActivity::class.java)
            intent.putExtra("ID_USUARIO", user.id)
            startActivity(password.context, intent, null)
        } else {
            Toast.makeText(password.context, "¡La contraseña es incorrecta!", Toast.LENGTH_LONG)
                .show()
        }

    }
}

