package com.example.gamesbreak.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.gamesbreak.R
import com.example.gamesbreak.databinding.ActivityPurchaseOfGameBinding
import com.example.gamesbreak.intermediaries.EpicGamesIntermediary
import com.example.gamesbreak.intermediaries.NakamaIntermediary
import com.example.gamesbreak.intermediaries.SteamIntermediary
import com.example.gamesbreak.services.PurchaseService
import com.example.gamesbreak.services.UserGameService
import java.text.DecimalFormat

class PurchaseOfGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPurchaseOfGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseOfGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showContent()
        onClickButtonCalculate()
    }


    private fun onClickButtonCalculate() {

        binding.btnCalculate.setOnClickListener {

            val priceOfGame: Double =
                binding.tvPriceGameCardPurchase.text.toString().filter { it != '$' }.toDouble()

            val priceTotal: Double = when (binding.rgIntermediaryOptions.checkedRadioButtonId) {
                binding.rbEpicGames.id -> PurchaseService.processPurchaseWithIntermediary(
                    EpicGamesIntermediary(), priceOfGame
                )

                binding.rbNakama.id -> PurchaseService.processPurchaseWithIntermediary(
                    NakamaIntermediary(), priceOfGame
                )

                else -> PurchaseService.processPurchaseWithIntermediary(
                    SteamIntermediary(), priceOfGame
                )
            }

            val percentage = (priceTotal.minus(priceOfGame).div(priceOfGame))

            val percentageFormatted = DecimalFormat("#.######")
            val priceTotalFormat = DecimalFormat("#.##")

            binding.apply {

                tvPriceOfGame.text =
                    buildString { append(getString(R.string.price_of_game_purchase) + priceOfGame) }

                tvPriceTotal.text = buildString {
                    append(
                        getString(R.string.price_total_purchase)
                                + priceTotalFormat.format(priceTotal)
                    )
                }

                tvPercentage.text = buildString {
                    append(
                        getString(R.string.percentage_of_intermediary)
                                + percentageFormatted.format(
                            percentage.times(100)
                        )
                             + "%"
                    )
                }

                cvPriceTotalPurchase.visibility = View.VISIBLE

                btnBuyGame.setOnClickListener {
                    val userId = intent.extras!!.getLong("ID_USER")
                    val gameId = intent.extras!!.getLong("ID_GAME")
                    val pricePurchase = priceTotalFormat.format(priceTotal)

                    when (UserGameService.userHasTheGame(userId, gameId)) {
                        true -> Toast.makeText(
                            btnBuyGame.context,
                            getString(R.string.user_has_the_game),
                            Toast.LENGTH_SHORT
                        ).show()

                        else -> {
                            val price = pricePurchase.replace(',', '.', true)

                            if (PurchaseService.recordPurchase(userId, gameId, price.toDouble())) {

                                Toast.makeText( btnBuyGame.context, getString(R.string.successful_purchase),
                                        Toast.LENGTH_LONG
                                    ).show()

                                Toast.makeText(
                                    btnBuyGame.context,
                                    PurchaseService.applyCashback(userId, price.toDouble()),
                                    Toast.LENGTH_LONG
                                ).show()

                            } else {
                                Toast
                                    .makeText(
                                        btnBuyGame.context,
                                        getString(R.string.rejected_purchase),
                                        Toast.LENGTH_SHORT
                                    ).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showContent() {
        val game = PurchaseService.getSelectedGame(intent.extras?.getLong("ID_GAME").toString())

        Glide.with(binding.ivSaleOfGame)
            .load(game.permalink)
            .into(binding.ivSaleOfGame)

        binding.tvTittleGameCardPurchase.text = game.name
        binding.tvPriceGameCardPurchase.text = buildString {
            append("$${game.price}")
        }

        binding.tvReleaseDateCardPurchase.text = buildString {
            append("${getString(R.string.msg_release_in)} ${game.releaseDate}")
        }
    }
}