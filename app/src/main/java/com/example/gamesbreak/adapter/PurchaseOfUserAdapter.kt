package com.example.gamesbreak.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesbreak.data.Purchase
import com.example.gamesbreak.databinding.ItemPurchaseOfUserBinding

class PurchaseOfUserAdapter(private val listOfPurchase: List<Purchase>) :
    RecyclerView.Adapter<PurchaseOfUserAdapter.PurchaseViewHolder>() {

    class PurchaseViewHolder(val binding: ItemPurchaseOfUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PurchaseViewHolder {
        val binding =
            ItemPurchaseOfUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PurchaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val purchase = listOfPurchase[position]

        holder.binding.tvPricePurchaseOfUser.text = buildString {
            append("$  ${purchase.amount}")
        }
        holder.binding.tvDatePurchase.text = purchase.createdDate

    }

    override fun getItemCount(): Int = listOfPurchase.size
}