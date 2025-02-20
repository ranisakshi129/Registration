package com.viw.registration.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viw.registration.databinding.CardItem1Binding
import com.viw.registration.model.CardItem1

class CardItem1Adapter(private val items: List<CardItem1>) : RecyclerView.Adapter<CardItem1Adapter.CardViewHolder>() {

    class CardViewHolder(val binding : CardItem1Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = CardItem1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            card1BackgroundImage.setImageResource(item.backgroundDrawable)
            card1OverlayImage.setImageResource(item.iconDrawable)
            cardButtonIv.setImageResource(item.buttonRawable)
            text1Tv.text = item.title
            text2Tv.text = item.value
            text3Tv.text = item.unit
            cardContainer.setBackgroundResource(item.gradientRes)
        }
    }

    override fun getItemCount() = items.size
}
