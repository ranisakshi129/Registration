package com.viw.registration.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.viw.registration.R
import com.viw.registration.adapter.CardItem1Adapter
import com.viw.registration.adapter.CardItem2Adapter
import com.viw.registration.databinding.FragmentHomeBinding
import com.viw.registration.model.CardItem1
import com.viw.registration.model.CardItem2

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var cardItem1Adapter: CardItem1Adapter
    private lateinit var cardItem2Adapter: CardItem2Adapter

    private val cardItem1List = mutableListOf<CardItem1>()
    private val cardItem2List = mutableListOf<CardItem2>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardItem1Adapter = CardItem1Adapter(cardItem1List)
        binding.recyclerView1.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerView1.adapter = cardItem1Adapter

        cardItem2Adapter = CardItem2Adapter(cardItem2List)
        binding.recyclerView2.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView2.adapter = cardItem2Adapter

        loadCardData()
    }

    private fun loadCardData() {

        cardItem1List.add(CardItem1("Heart Rate", "78", "bpm", R.drawable.card_ellipse, R.drawable.fire, R.drawable.arrow, R.drawable.gradient_card1))
        cardItem1List.add(CardItem1("Blood Pressure", "120/80", "mmHg", R.drawable.card_ellipse, R.drawable.heart, R.drawable.arrow, R.drawable.gradient_card2))
        cardItem1List.add(CardItem1("Heart Rate", "78", "bpm", R.drawable.card_ellipse, R.drawable.fire, R.drawable.arrow, R.drawable.gradient_card1))
        cardItem1List.add(CardItem1("Blood Pressure", "120/80", "mmHg", R.drawable.card_ellipse, R.drawable.heart, R.drawable.arrow, R.drawable.gradient_card2))
        cardItem1List.add(CardItem1("Heart Rate", "78", "bpm", R.drawable.card_ellipse, R.drawable.fire, R.drawable.arrow, R.drawable.gradient_card1))
        cardItem1List.add(CardItem1("Blood Pressure", "120/80", "mmHg", R.drawable.card_ellipse, R.drawable.heart, R.drawable.arrow, R.drawable.gradient_card2))
        cardItem1Adapter.notifyDataSetChanged()

        cardItem2List.add(CardItem2("PHARMACY", R.drawable.pharmacy_image))
        cardItem2List.add(CardItem2("NUTRITION", R.drawable.nutrition_image))
        cardItem2List.add(CardItem2("PHARMACY", R.drawable.pharmacy_image))
        cardItem2List.add(CardItem2("NUTRITION", R.drawable.nutrition_image))
        cardItem2List.add(CardItem2("PHARMACY", R.drawable.pharmacy_image))
        cardItem2List.add(CardItem2("NUTRITION", R.drawable.nutrition_image))
        cardItem2List.add(CardItem2("PHARMACY", R.drawable.pharmacy_image))
        cardItem2List.add(CardItem2("NUTRITION", R.drawable.nutrition_image))

        cardItem2Adapter.notifyDataSetChanged()
    }
}



