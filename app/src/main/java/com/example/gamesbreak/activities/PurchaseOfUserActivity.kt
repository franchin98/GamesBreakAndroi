package com.example.gamesbreak.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamesbreak.adapter.PurchaseOfUserAdapter
import com.example.gamesbreak.databinding.ActivityPurchaseOfUserBinding
import com.example.gamesbreak.services.PurchaseService

class PurchaseOfUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPurchaseOfUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPurchaseOfUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showContent()
    }

    private fun showContent() {
        val userId = intent.extras?.getLong("ID_USER")
        val listPurchasesOfUser = PurchaseService.getPurchasesOfUser(userId!!)

        binding.recyclerViewPurchaseOfUser.adapter = PurchaseOfUserAdapter(listPurchasesOfUser)
        binding.recyclerViewPurchaseOfUser.layoutManager = LinearLayoutManager(this)

    }
}