package com.example.gamesbreak.services

import com.example.gamesbreak.data.Game
import com.example.gamesbreak.repositories.GameRepository

class PurchaseService {

    companion object {
        fun getSelectedGame(idGame: String): Game = GameRepository.getByID(idGame)!!
    }

}
