package com.example.gamesbreak.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.gamesbreak.R
import com.example.gamesbreak.data.UserCredentials
import com.example.gamesbreak.data.UserPreferences
import com.example.gamesbreak.databinding.ActivityHomeBinding
import com.example.gamesbreak.ui.authentication.AuthenticationActivity
import com.example.gamesbreak.utils.startNewActivity
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var user: UserCredentials? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val storage = UserPreferences(this)

        storage.userCredentials.asLiveData().observe(this) { credentials ->
            user = credentials
            if (credentials != null) {
                binding.tvGreetingHome.text = String.format(getString(R.string.greeting_home), user?.name)
                setUpClickListeners()
            } else {
                startNewActivity(AuthenticationActivity::class.java)
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_logout -> {
                val storage = UserPreferences(this)
                lifecycleScope.launch {
                    storage.clear()
                    startNewActivity(AuthenticationActivity::class.java)
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

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

    }
}