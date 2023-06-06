package com.example.gamesbreak.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamesbreak.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nameUser = intent.extras!!.getString("NAME_USER")
        binding.tvGreetingUser.text = buildString {
            append("¡Hola, ")
            append("$nameUser!")
        }
        showGamesToSale()
        showGamesOfUser()
    }

    private fun showGamesOfUser() {
        val userId = intent.extras?.getLong("ID_USER")

        binding.cvMyGames.setOnClickListener {
            val intentMyGames = Intent(this, GameOfUserActivity::class.java)
            intentMyGames.putExtra("ID_USER", userId)
            startActivity(intentMyGames)
        }
    }


    private fun showGamesToSale() {
        binding.cvBuyGames.setOnClickListener {
            val intentGameRecyclerActivity = Intent(this, GameToSaleActivity::class.java)
            startActivity(intentGameRecyclerActivity)
        }
    }
}