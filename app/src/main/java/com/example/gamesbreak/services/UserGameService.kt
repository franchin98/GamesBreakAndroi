package com.example.gamesbreak.services

import com.example.gamesbreak.data.Game
import com.example.gamesbreak.data.GameBuy
import com.example.gamesbreak.repositories.GameRepository
import com.example.gamesbreak.repositories.PurchaseRepository

class UserGameService {
    companion object {
        private val purchaseRepository = PurchaseRepository
        private val gameRepository = GameRepository

        fun getGamesOfUser(userId: Long): List<GameBuy> {
            val listOfGames: MutableList<GameBuy> = mutableListOf()

            for (purchase in purchaseRepository.getAll()) {
                if (purchase.userId == userId) {
                    val game = gameRepository.getByID(purchase.gameId.toString())
                    val gameBuy = GameBuy(game!!.permalink, game.name, purchase.createdDate, purchase.amount)
                    listOfGames.add(gameBuy)
                }
            }

            return listOfGames
        }

        fun userHasTheGame(userId: Long, gameId: Long): Boolean {
            val gamesOfUser = mutableListOf<Game>()

            for(purchase in purchaseRepository.getAll()) {
                if(purchase.userId == userId)
                    gamesOfUser.add(GameRepository.getByID(purchase.gameId.toString())!!)
            }

            val game = gameRepository.getByID(gameId.toString())

            return gamesOfUser.contains(game)
        }
    }

}
