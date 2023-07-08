package com.example.gamesbreak.services

import com.example.gamesbreak.data.Game
import com.example.gamesbreak.data.Purchase
import com.example.gamesbreak.intermediaries.IntermediaryInterface
import com.example.gamesbreak.repositories.GameRepository
import com.example.gamesbreak.repositories.PurchaseRepository
import com.example.gamesbreak.repositories.UserRepository
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
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
            datePurchase: LocalDate = LocalDate.now(ZoneId.of("America/Argentina/Buenos_Aires"))
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

        fun applyCashback(userId: Long, pricePurchase: Double): String {
            val THREE_MONTHS = 3
            val TWELVE_MONTHS = 12

            val user = UserRepository.getByIdNumeric(userId)!!


            val createdDateUser = user.createdDate.replace('/', '-')

            val dateDiff = Period.between(LocalDate.parse(createdDateUser), LocalDate.now())
            val cashback: Double
            when(dateDiff.months) {
                in 0..THREE_MONTHS -> {  val FIVE_PERCENT = 0.05
                                    cashback = pricePurchase.times(FIVE_PERCENT)
                                    user.money = user.money.plus(cashback)
                                    return "Cashback otorgado: $$cashback"
                                    }

                in 4.. TWELVE_MONTHS -> { val THREE_PERCENT = 0.03
                                     cashback = pricePurchase.times(THREE_PERCENT)

                                      user.money = user.money.plus(cashback)
                                      return "Cashback otorgado: $$cashback"
                                     }

            }

            return "¡No aplica cashback!"
        }


        fun getPurchasesOfUser(userId: Long): List<Purchase> {
            return purchaseRepository.getAll().filter { it.userId == userId }
        }


    }
}


