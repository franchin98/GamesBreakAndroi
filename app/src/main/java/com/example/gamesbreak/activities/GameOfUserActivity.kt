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

        if (UserGameService.getGamesOfUser(intent.extras!!.getLong("ID_USER")).isEmpty()) {
            binding.tvVisibilityGone.visibility = View.VISIBLE
            binding.tvVisibilityGone.text = getString(R.string.no_tienes_juegos_comprados)
        }


        val onClickGame = { game: Game ->
            Toast.makeText(this, "Juego: ${game.name}, precio: ${game.price}", Toast.LENGTH_LONG)
                .show()
        }

        binding.recyclerViewGamesOfUser.adapter = GameOfUserAdapter(
            UserGameService.getGamesOfUser(intent.extras!!.getLong("ID_USER")), onClickGame
        )
        binding.recyclerViewGamesOfUser.layoutManager = LinearLayoutManager(this)

    }


}