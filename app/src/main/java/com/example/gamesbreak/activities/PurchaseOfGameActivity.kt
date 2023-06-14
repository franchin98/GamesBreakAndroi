package com.example.gamesbreak.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.gamesbreak.R
import com.example.gamesbreak.databinding.ActivityPurchaseOfGameBinding
import com.example.gamesbreak.services.PurchaseService

class PurchaseOfGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPurchaseOfGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseOfGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showContent()
    }

    private fun showContent() {
        val game = PurchaseService.getSelectedGame( intent.extras?.getLong("ID_GAME").toString() )

        Glide.with(binding.ivSaleOfGame)
            .load(game.permalink)
            .into(binding.ivSaleOfGame)

        binding.tvTittleGameCardPurchase.text = game.name
        binding.tvPriceGameCardPurchase.text = buildString {
            append("$${game.price}")
        }


        binding.tvReleaseDateCardPurchase.text = buildString {
            append("${getString(R.string.msg_release_in)} ${game.releaseDate}")
        }
    }
}