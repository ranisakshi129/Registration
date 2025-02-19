package com.viw.registration.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.viw.registration.R
import com.viw.registration.model.Card

class CardAdapter(private val card: List<Card >) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val cardData = card[position]
        holder.card1backgroundImage.setImageResource(cardData.backgroundImage)
        holder.card1overlayImage.setImageResource(cardData.image)
        holder.cardButtonIv.setImageResource(cardData.buttonImage)
        holder.text1.text = cardData.text1
        holder.text2.text = cardData.text2
        holder.text3.text = cardData.text3
    }

    override fun getItemCount(): Int {
        return card.size
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card1backgroundImage: ImageView = itemView.findViewById(R.id.card1_backgroundImage)
        val card1overlayImage: ImageView = itemView.findViewById(R.id.card1_overlayImage)
        val cardButtonIv: ImageView = itemView.findViewById(R.id.cardButtonIv)
        val text1: TextView = itemView.findViewById(R.id.text1Tv)
        val text2: TextView = itemView.findViewById(R.id.text2Tv)
        val text3: TextView = itemView.findViewById(R.id.text3Tv)
    }

}



