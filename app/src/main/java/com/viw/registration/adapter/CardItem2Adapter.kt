package com.viw.registration.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viw.registration.databinding.CardItem2Binding
import com.viw.registration.model.CardItem2

class CardItem2Adapter(private val items: List<CardItem2>) :
    RecyclerView.Adapter<CardItem2Adapter.ViewHolder>() {

    class ViewHolder(val binding : CardItem2Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = CardItem2Binding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            itemImage.setImageResource(item.imageResId)
            itemText.text = item.title
        }
    }

    override fun getItemCount(): Int = items.size
}
