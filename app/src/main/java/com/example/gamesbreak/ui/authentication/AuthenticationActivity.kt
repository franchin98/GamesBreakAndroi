package com.example.gamesbreak.ui.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.gamesbreak.R

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        // Reemplazar el contenido del contenedor de fragmentos con LoginFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, LoginFragment())
            .commit()
    }
}