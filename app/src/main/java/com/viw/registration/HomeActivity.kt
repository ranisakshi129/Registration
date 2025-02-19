package com.viw.registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.viw.registration.adapter.CardAdapter
import com.viw.registration.fragments.AppointmentsFragment
import com.viw.registration.fragments.EventsFragment
import com.viw.registration.fragments.HomeFragment
import com.viw.registration.fragments.MarketPlaceFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        loadFragment(HomeFragment())

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_appointments -> loadFragment(AppointmentsFragment())
                R.id.nav_events -> loadFragment(EventsFragment())
                R.id.nav_marketplace -> loadFragment(MarketPlaceFragment())
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragme_container, fragment)
            .commit()
    }

}