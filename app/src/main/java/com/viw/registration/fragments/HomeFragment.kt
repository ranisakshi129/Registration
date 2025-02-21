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
import com.viw.registration.adapter.CardItem2Adapter
import com.viw.registration.model.Card
import com.viw.registration.model.CardItem2

class HomeFragment : Fragment() {

    private lateinit var cardItem2Rv: RecyclerView
    private lateinit var cardItem2Adapter: CardItem2Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        cardItem2Rv = view.findViewById(R.id.cardItem2Rv)
        cardItem2Rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val items = listOf(
            CardItem2("Pharmacy", R.drawable.pharmacy_image),
            CardItem2("Nutrition", R.drawable.nutrition_image),
            CardItem2("Doctors", R.drawable.doctor_image),
        )

        cardItem2Adapter = CardItem2Adapter(items)  // Use the correct adapter
        cardItem2Rv.adapter = cardItem2Adapter

        return view
    }
}
