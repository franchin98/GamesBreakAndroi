package com.example.gamesbreak.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamesbreak.data.Game
import com.example.gamesbreak.databinding.ItemGameOfUserBinding

class GameOfUserAdapter(
    private val listOfGamesOfUser: List<Game>,
    private val onClickItemGame: (Game) -> Unit
) : RecyclerView.Adapter<GameOfUserAdapter.GameOfUserViewHolder>() {

    class GameOfUserViewHolder(val binding: ItemGameOfUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameOfUserViewHolder {
        val binding =
            ItemGameOfUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return GameOfUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameOfUserViewHolder, position: Int) {
        val game = listOfGamesOfUser[position]

        holder.binding.tvTittleGameOfUser.text = game.name
        Glide.with(holder.binding.ivGameOfUserItem).load(game.permalink)
            .into(holder.binding.ivGameOfUserItem)

        holder.binding.cardGameOfUser.setOnClickListener {
            onClickItemGame(game)
        }
    }

    override fun getItemCount(): Int = listOfGamesOfUser.size


}