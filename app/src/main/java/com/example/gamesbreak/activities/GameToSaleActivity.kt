package com.example.gamesbreak.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamesbreak.MainActivity
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

            if(intent.extras?.getLong("ID_USER").toString().isEmpty())
                startActivity(Intent(this, MainActivity::class.java))


            val intentPurchaseGame = Intent(this, PurchaseOfGameActivity::class.java)
            intentPurchaseGame.putExtra("ID_GAME", game.id)
            intentPurchaseGame.putExtra("ID_USER", intent.extras!!.getLong("ID_USER"))
            startActivity(intentPurchaseGame)
        }

        bindingGame.reciclerViewGame.adapter =
            GameAdapter(GameRepository.getAll(), selectGameOnClickListener)
        bindingGame.reciclerViewGame.layoutManager = LinearLayoutManager(this)
    }
}