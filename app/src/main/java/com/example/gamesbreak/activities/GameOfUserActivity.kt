package com.example.gamesbreak.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamesbreak.R
import com.example.gamesbreak.adapter.GameOfUserAdapter
import com.example.gamesbreak.data.Game
import com.example.gamesbreak.databinding.ActivityGameOfUserBinding
import com.example.gamesbreak.services.UserGameService


class GameOfUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameOfUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameOfUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showGamesOfUser()
    }

    private fun showGamesOfUser() {
        val gamesOfUser = UserGameService.getGamesOfUser(intent.extras!!.getLong("ID_USER"))

        if (gamesOfUser.isEmpty()) {
            binding.tvVisibilityGone.visibility = View.VISIBLE
            binding.tvVisibilityGone.text = getString(R.string.no_tienes_juegos_comprados)
        }

        val onClickGame = { game: Game ->
            Toast.makeText(
                this,
                "${getString(R.string.name_of_game_of_user)} ${game.name}, ${getString(R.string.price_game_of_user)} ${game.price}",
                Toast.LENGTH_LONG
            ).show()
        }

        binding.recyclerViewGamesOfUser.adapter = GameOfUserAdapter(gamesOfUser, onClickGame)
        binding.recyclerViewGamesOfUser.layoutManager = LinearLayoutManager(this)

    }


}