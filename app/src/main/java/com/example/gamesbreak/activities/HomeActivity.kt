package com.example.gamesbreak.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.gamesbreak.MainActivity
import com.example.gamesbreak.R
import com.example.gamesbreak.data.UserCredentials
import com.example.gamesbreak.data.UserPreferences
import com.example.gamesbreak.databinding.ActivityHomeBinding
import com.example.gamesbreak.ui.authentication.AuthenticationActivity
import com.example.gamesbreak.utils.startNewActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var user: UserCredentials? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val storage = UserPreferences(this)

        storage.userCredentials.asLiveData().observe(this, Observer { credentials ->
            user = credentials
            if (credentials != null) {
                binding.tvGreetingUser.text = buildString {
                    append(getString(R.string.greeting_home))
                    append(" ${user?.name}!")
                }
                setUpClickListeners()
            } else {
                startNewActivity(AuthenticationActivity::class.java)
            }
        })
    }
    private fun setUpClickListeners() {
        val userId = this.user?.id

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

        binding.cvMyPurchases.setOnClickListener {
            val intentPurchaseOfUserActivity = Intent(this, PurchaseOfUserActivity::class.java)
            intentPurchaseOfUserActivity.putExtra("ID_USER", userId)
            startActivity(intentPurchaseOfUserActivity)

        }

    }
}