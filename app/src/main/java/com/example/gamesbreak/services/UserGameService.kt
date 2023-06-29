package com.example.gamesbreak.services

import com.example.gamesbreak.data.Game
import com.example.gamesbreak.repositories.GameRepository
import com.example.gamesbreak.repositories.PurchaseRepository

class UserGameService {
    companion object {
        private val purchases = PurchaseRepository.getAll()

        fun getGamesOfUser(userId: Long): List<Game> {
            val listOfGames: MutableList<Game> = mutableListOf()

            for (purchase in purchases) {
                if (purchase.userId == userId) {
                    listOfGames.add(GameRepository.getByID(purchase.gameId.toString())!!)
                }
            }

            return listOfGames
        }
    }

}
