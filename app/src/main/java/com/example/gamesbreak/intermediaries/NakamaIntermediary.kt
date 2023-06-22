package com.example.gamesbreak.intermediaries

import java.time.Clock
import java.time.DayOfWeek
import java.time.LocalDate

class NakamaIntermediary: IntermediaryInterface {
    val FIRST_COMMISSION = 0.03
    val SECOND_COMMISSION = 0.0075
    override fun processPurchase(price: Double): Double {
        val currentDate: LocalDate = LocalDate.now(Clock.systemDefaultZone())

        return if (currentDate.dayOfWeek == DayOfWeek.SATURDAY || currentDate.dayOfWeek == DayOfWeek.SUNDAY) {
            (price.plus(price.times(FIRST_COMMISSION)))
        } else {
            (price.plus(price.times(SECOND_COMMISSION)))
        }
    }
}