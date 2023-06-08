package com.example.gamesbreak.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.gamesbreak.databinding.ActivityPurchaseOfGameBinding
import com.example.gamesbreak.repositories.GameRepository

class PurchaseOfGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPurchaseOfGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseOfGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val game = GameRepository.getByID(intent.extras?.getLong("ID_GAME").toString())

        Glide.with(binding.ivSaleOfGame).load(game?.permalink).into(binding.ivSaleOfGame)



    }
}