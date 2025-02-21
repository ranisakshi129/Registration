package com.viw.registration.adapter

import android.widget.RelativeLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.viw.registration.R
import com.viw.registration.model.CardItem1

class CardItem1Adapter(private val items: List<CardItem1>) : RecyclerView.Adapter<CardItem1Adapter.CardViewHolder>() {

    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val backgroundImage: ImageView = view.findViewById(R.id.card1_backgroundImage)
        val overlayImage: ImageView = view.findViewById(R.id.card1_overlayImage)
        val titleTv: TextView = view.findViewById(R.id.text1Tv)
        val valueTv: TextView = view.findViewById(R.id.text2Tv)
        val unitTv: TextView = view.findViewById(R.id.text3Tv)
        val cardButton : ImageView = view.findViewById(R.id.cardButtonIv)
        val cardContainer : RelativeLayout = view.findViewById(R.id.cardContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item1, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = items[position]
        holder.backgroundImage.setImageResource(item.backgroundDrawable)
        holder.overlayImage.setImageResource(item.iconDrawable)
        holder.cardButton.setImageResource(item.buttonRawable)
        holder.titleTv.text = item.title
        holder.valueTv.text = item.value
        holder.unitTv.text = item.unit
        holder.cardContainer.setBackgroundResource(item.gradientRes)
    }

    override fun getItemCount() = items.size
}
