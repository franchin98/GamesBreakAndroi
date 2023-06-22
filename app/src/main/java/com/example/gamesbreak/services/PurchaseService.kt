package com.example.gamesbreak.services

import com.example.gamesbreak.data.Game
import com.example.gamesbreak.data.Purchase
import com.example.gamesbreak.intermediaries.IntermediaryInterface
import com.example.gamesbreak.repositories.GameRepository
import com.example.gamesbreak.repositories.PurchaseRepository
import com.example.gamesbreak.repositories.UserRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PurchaseService {

    companion object {
        private val gameRepository = GameRepository
        private val userRepository = UserRepository
        private val purchaseRepository = PurchaseRepository

        fun getSelectedGame(idGame: String): Game = gameRepository.getByID(idGame)!!
        fun processPurchaseWithIntermediary(
            intermediary: IntermediaryInterface,
            priceOfGame: Double
        ): Double =
            intermediary.processPurchase(priceOfGame)

        fun recordPurchase(
            userId: Long,
            gameId: Long,
            pricePurchase: Double,
            datePurchase: LocalDate
        )
        : Boolean {
            val user = userRepository.getByIdNumeric(userId)
            val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

            if(user!!.money >= pricePurchase) {
                val datePurchaseStr = dateTimeFormatter.format(datePurchase)
                val idPurchase = purchaseRepository.getAll().size.plus(1).toLong()
                val newPurchase = Purchase(idPurchase, userId, gameId, pricePurchase, datePurchaseStr)
                purchaseRepository.addItem(newPurchase)
                user.money = user.money.minus(pricePurchase)
                return true
            }

            return false
        }

    }
}


