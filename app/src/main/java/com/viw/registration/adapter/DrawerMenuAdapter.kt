package com.viw.registration.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.viw.registration.R
import com.viw.registration.model.MenuItem

class DrawerMenuAdapter(
    private val menuList: List<MenuItem>,
    private val listener: (MenuItem) -> Unit
) : RecyclerView.Adapter<DrawerMenuAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.menu_icon)
        val title: TextView = view.findViewById(R.id.menu_title)
        val arrow: ImageView = view.findViewById(R.id.arrow_icon)
        val line : ImageView = view.findViewById(R.id.drawer_line)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.drawer_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = menuList[position]
        holder.icon.setImageResource(item.icon)
        holder.title.text = item.title
        holder.arrow.setImageResource(R.drawable.drawer_arrow)
        holder.line.setImageResource(R.drawable.drawer_line)
       // holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount() = menuList.size
}
