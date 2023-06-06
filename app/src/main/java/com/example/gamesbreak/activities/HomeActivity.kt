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
        showGamesToSale()
        showGamesOfUser()
    }

    private fun showGamesOfUser() {
        val userId = intent.extras?.getLong("ID_USUARIO")

        binding.cvMyGames.setOnClickListener {
            val intentMyGames = Intent(this, GameOfUserActivity::class.java)
            intentMyGames.putExtra("ID_USUARIO", userId)
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