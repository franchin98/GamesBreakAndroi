package com.example.gamesbreak.services

import com.example.gamesbreak.data.Game
import com.example.gamesbreak.repositories.GameRepository
import com.example.gamesbreak.repositories.PurchaseRepository

class UserGameService {
    companion object {
        private val purchases = PurchaseRepository.getAll()
        private val gamesRepository = GameRepository

        fun getGamesOfUser(userId: Long): List<Game> {
            val listOfGames: MutableList<Game> = mutableListOf()

            for (purchase in purchases) {
                if (purchase.userId == userId) {
                    listOfGames.add(GameRepository.getByID(purchase.gameId.toString())!!)
                }
            }

            return listOfGames
        }

        fun userHasTheGame(userId: Long, gameId: Long): Boolean {
            val gamesOfUser = getGamesOfUser(userId)
            val game = gamesRepository.getByID(gameId.toString())

            return gamesOfUser.contains(game)
        }
    }

}
