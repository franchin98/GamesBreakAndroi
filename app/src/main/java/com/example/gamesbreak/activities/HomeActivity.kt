package com.example.gamesbreak.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamesbreak.MainActivity
import com.example.gamesbreak.R
import com.example.gamesbreak.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nameUser = intent.extras?.getString("NAME_USER")

        if (nameUser.isNullOrEmpty())
            startActivity(Intent(this, MainActivity::class.java))
        else {
            binding.tvGreetingUser.text = buildString {
                append(getString(R.string.greeting_home))
                append(" $nameUser!")
            }
            setUpClickListeners()
        }
    }
    private fun setUpClickListeners() {
        val userId = intent.extras?.getString("ID_USER")?.toLong()

        binding.cvMyGames.setOnClickListener {
            val intentMyGames = Intent(this, GameOfUserActivity::class.java)
            intentMyGames.putExtra("ID_USER", userId)
            startActivity(intentMyGames)
        }

        binding.cvBuyGames.setOnClickListener {
            val intentGameRecyclerActivity = Intent(this, GameToSaleActivity::class.java)
            intentGameRecyclerActivity.putExtra("ID_USER", userId)
            startActivity(intentGameRecyclerActivity)
        }

    }
}