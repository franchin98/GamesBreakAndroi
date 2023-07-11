package com.example.gamesbreak.intermediaries

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId

class NakamaIntermediary: IntermediaryInterface {
    private val FIRST_COMMISSION = 0.03
    private val SECOND_COMMISSION = 0.0075
    override fun processPurchase(price: Double): Double {
        val currentDate: LocalDate = LocalDate.now(ZoneId.of("America/Argentina/Buenos_Aires"))

        return if (currentDate.dayOfWeek == DayOfWeek.SATURDAY || currentDate.dayOfWeek == DayOfWeek.SUNDAY) {
            (price.plus(price.times(FIRST_COMMISSION)))
        } else {
            (price.plus(price.times(SECOND_COMMISSION)))
        }
    }
}