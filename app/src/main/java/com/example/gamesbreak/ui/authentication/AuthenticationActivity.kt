package com.example.gamesbreak.ui.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamesbreak.R

class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        // Reemplazar el contenido del contenedor de fragmentos con LoginFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, LoginFragment())
            .commit()
    }

}
