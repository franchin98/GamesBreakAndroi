package com.example.gamesbreak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.gamesbreak.activities.HomeActivity
import com.example.gamesbreak.data.User
import com.example.gamesbreak.databinding.ActivityMainBinding
import com.example.gamesbreak.repositories.UserRepository

class MainActivity : AppCompatActivity() {

    private lateinit var bindingMain: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        val btnLogin: AppCompatButton = bindingMain.btnLogIn

        btnLogin.setOnClickListener {
            validateLogin()
        }
    }

    private fun validateLogin() {
        when (val user = validateUser()) {
            is User -> validatePassword(user)
            else -> if (bindingMain.etUserName.text.toString().isEmpty() ||
                bindingMain.etPassword.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Verifique los datos ingresados.", Toast.LENGTH_LONG).show()
            } else
                Toast.makeText(this, "¡El usuario no existe!", Toast.LENGTH_LONG).show()
        }
    }

    private fun validatePassword(anUser: User) {
        val userPasswordInputEditText = bindingMain.etPassword.text.toString()

        if (anUser.password == userPasswordInputEditText) {
            val intentHomeActivity = Intent(this, HomeActivity::class.java)
            startActivity(intentHomeActivity)
        } else
            Toast.makeText(this, "¡Contraseña incorrecta!", Toast.LENGTH_LONG).show()
    }

    private fun validateUser(): User? {
        val userRepository = UserRepository
        val userNameInputEditText = bindingMain.etUserName.text.toString()

        return userRepository.getByID(userNameInputEditText)
    }
}