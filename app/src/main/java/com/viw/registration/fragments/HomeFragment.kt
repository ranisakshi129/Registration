package com.viw.registration.fragments

import com.viw.registration.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.viw.registration.adapter.CardAdapter
import com.viw.registration.model.Card

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardAdapter
    private lateinit var viewPager2: ViewPager2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val view = inflater.inflate(R.layout.fragment_home, container, false)
       recyclerView = view.findViewById(R.id.recyclerView)
       recyclerView.layoutManager = LinearLayoutManager(requireContext())
       viewPager2 = view.findViewById(R.id.viewPager)

        val cardData = listOf(
            Card(R.drawable.card_button_image,R.drawable.fire, R.drawable.card_ellipse , "Calories burn" , "1005" ,"Calorie"),
            Card(R.drawable.card_button_image,R.drawable.heart, R.drawable.card_ellipse , "Heart Rate" , "72" ,"bpm"),
            Card(R.drawable.card_button_image,R.drawable.lungs, R.drawable.card_ellipse , "SPO2" , "96%" ,"Spm"),
            )

        adapter = CardAdapter(cardData)
        recyclerView.adapter = adapter

        return view
    }
}
