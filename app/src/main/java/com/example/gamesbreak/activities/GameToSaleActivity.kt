package com.example.gamesbreak.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamesbreak.adapter.GameAdapter
import com.example.gamesbreak.data.Game
import com.example.gamesbreak.databinding.ActivityGameReciclerBinding
import com.example.gamesbreak.repositories.GameRepository

class GameToSaleActivity : AppCompatActivity() {

    private lateinit var bindingGame: ActivityGameReciclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingGame = ActivityGameReciclerBinding.inflate(layoutInflater)
        setContentView(bindingGame.root)
        showGamesToSale()
    }

    private fun showGamesToSale() {
        val selectGameOnClickListener = { game: Game ->
            Toast.makeText(
                this,
                "Nombre del juego: ${game.name}, Precio: ${game.price}",
                Toast.LENGTH_LONG
            ).show()
        }

        bindingGame.reciclerViewGame.adapter =
            GameAdapter(GameRepository.getAll()!!, selectGameOnClickListener)
        bindingGame.reciclerViewGame.layoutManager = LinearLayoutManager(this)
    }
}