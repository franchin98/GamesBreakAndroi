package com.example.gamesbreak.intermediaries

class SteamIntermediary : IntermediaryInterface {
    private val COMMISSION = 0.02
    override fun processPurchase(price: Double): Double {
        return (price.plus(price.times(COMMISSION)))
    }
}