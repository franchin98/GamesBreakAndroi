package com.example.gamesbreak.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamesbreak.data.Game
import com.example.gamesbreak.databinding.ItemGameBinding


class GameAdapter(
    private val games: List<Game>, private val setViewGameDescription: (Game) -> Unit
) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(val binding: ItemGameBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val gameBinding =
            ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return GameViewHolder(gameBinding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]

        holder.binding.tvGameName.text = game.name
        holder.binding.tvPriceGame.text = buildString {
            append("$")
            append(game.price)
        }
        Glide.with(holder.binding.ivItemGame).load(game.permalink).into(holder.binding.ivItemGame)
        holder.binding.cardsGame.setOnClickListener {
            setViewGameDescription(game)
        }
    }

    override fun getItemCount(): Int = games.size
}