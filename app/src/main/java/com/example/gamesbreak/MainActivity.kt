package com.example.gamesbreak


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamesbreak.ui.authentication.AuthenticationActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Iniciar AuthenticationActivity
        val intent = Intent(this, AuthenticationActivity::class.java)
        startActivity(intent)

        // Finalizar MainActivity
        finish()
    }

}