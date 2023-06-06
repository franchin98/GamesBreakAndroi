package com.example.gamesbreak


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.gamesbreak.databinding.ActivityMainBinding
import com.example.gamesbreak.services.LoginService

class MainActivity : AppCompatActivity() {

    private lateinit var bindingMain: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        val btnLogin: AppCompatButton = bindingMain.btnLogIn

        btnLogin.setOnClickListener {
            LoginService.validateLogin(
                bindingMain.etUserName,
                bindingMain.etPassword
            )
        }
    }
}