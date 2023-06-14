package com.example.gamesbreak


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gamesbreak.activities.HomeActivity
import com.example.gamesbreak.data.User
import com.example.gamesbreak.databinding.ActivityMainBinding
import com.example.gamesbreak.services.LoginService

class MainActivity : AppCompatActivity() {

    private lateinit var bindingMain: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        val btnLogin = bindingMain.btnLogIn

        btnLogin.setOnClickListener {
            runLogIn()
        }
    }

    private fun runLogIn() {
        val user = LoginService.validateLogin(
            bindingMain.etUserName.text.toString(),
            bindingMain.etPassword.text.toString()
        )

        if (user is User) {
            val intentHomeActivity = Intent(this, HomeActivity::class.java)
            intentHomeActivity.putExtra("NAME_USER", user.name)
            intentHomeActivity.putExtra("ID_USER", user.id)
            startActivity(intentHomeActivity)
        } else
            Toast.makeText(this, getString(R.string.menssage_error_login), Toast.LENGTH_LONG).show()
    }

}