package com.viw.registration.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viw.registration.databinding.DrawerItemsBinding
import com.viw.registration.model.MenuItem

class DrawerMenuAdapter(
    private val menuList: List<MenuItem>,
    private val listener: (MenuItem) -> Unit
) : RecyclerView.Adapter<DrawerMenuAdapter.ViewHolder>() {

    class ViewHolder(val binding: DrawerItemsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DrawerItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = menuList[position]
        with(holder.binding) {
            menuIcon.setImageResource(item.icon)
            menuTitle.text = item.title
            arrowIcon.setImageResource(com.viw.registration.R.drawable.drawer_arrow)
            drawerLine.setImageResource(com.viw.registration.R.drawable.drawer_line)
            root.setOnClickListener { listener(item) }
        }
    }

    override fun getItemCount() = menuList.size
}
