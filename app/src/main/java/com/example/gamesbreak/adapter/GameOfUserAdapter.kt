package com.example.gamesbreak.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamesbreak.data.GameBuy
import com.example.gamesbreak.databinding.ItemGameOfUserBinding

class GameOfUserAdapter(
    private val listOfGamesOfUser: List<GameBuy>,
    private val onClickItemGame: (GameBuy) -> Unit
) : RecyclerView.Adapter<GameOfUserAdapter.GameOfUserViewHolder>() {

    class GameOfUserViewHolder(val binding: ItemGameOfUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameOfUserViewHolder {
        val binding =
            ItemGameOfUserBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

        return GameOfUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameOfUserViewHolder, position: Int) {
        val purchasedGame = listOfGamesOfUser[position]


        holder.binding.tvTittleGameOfUser.text = purchasedGame.titleGame
        Glide.with(holder.binding.ivGameOfUserItem).load(purchasedGame.sourceImage)
            .into(holder.binding.ivGameOfUserItem)

        holder.binding.tvDatePuchase.text = buildString {
            append("Fecha de compra: ")
            append(purchasedGame.datePurchase)
        }

        holder.binding.tvPricePurchase.text = buildString {
          append("Precio de compra: $")
          append(purchasedGame.amountPurchase.toString())
        }



        holder.binding.cardGameOfUser.setOnClickListener {
            onClickItemGame(purchasedGame)
        }
    }

    override fun getItemCount(): Int = listOfGamesOfUser.size


}