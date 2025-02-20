package com.viw.registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.viw.registration.adapter.DrawerMenuAdapter
import com.viw.registration.databinding.ActivityHomeBinding
import com.viw.registration.fragments.AppointmentsFragment
import com.viw.registration.fragments.EventsFragment
import com.viw.registration.fragments.HomeFragment
import com.viw.registration.fragments.MarketPlaceFragment
import com.viw.registration.model.MenuItem

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var drawerMenuAdapter: DrawerMenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Prevent loading HomeFragment twice
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_appointments -> loadFragment(AppointmentsFragment())
                R.id.nav_events -> loadFragment(EventsFragment())
                R.id.nav_marketplace -> loadFragment(MarketPlaceFragment())
            }
            true
        }

        binding.menuIcon.setOnClickListener {
            if (!binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
        }

        // ✅ Drawer Menu Data
        val menu = listOf(
            MenuItem("My Profile", R.drawable.drawer_profile),
            MenuItem("My Appointments", R.drawable.drawer_appointments),
            MenuItem("Medical Records", R.drawable.drawer_medical),
            MenuItem("Consultations", R.drawable.drawer_consultations),
            MenuItem("Saved Doctors", R.drawable.drawer_doctors),
            MenuItem("My Event", R.drawable.drawer_doctors),
            MenuItem("Terms and Conditions", R.drawable.drawer_termsconditions),
            MenuItem("Privacy Policy", R.drawable.drawer_policy),
            MenuItem("Help & FAQs", R.drawable.drawer_helps),
            MenuItem("Sign Out", R.drawable.drawer_signout)
        )

        drawerMenuAdapter = DrawerMenuAdapter(menu) { binding.drawerLayout.closeDrawers() }

        // ✅ Ensure drawerMenu RecyclerView is only for navigation items
        binding.recyclerMenu.layoutManager = LinearLayoutManager(this)
        binding.recyclerMenu.adapter = drawerMenuAdapter
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragme_container, fragment) // ✅ Fixed ID
            .commit()
    }
}



//package com.viw.registration
//
//import android.os.Bundle
//
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.content.ContentProviderCompat.requireContext
//import androidx.core.view.GravityCompat
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.viw.registration.adapter.CardItem1Adapter
//import com.viw.registration.adapter.CardItem2Adapter
//import com.viw.registration.adapter.DrawerMenuAdapter
//import com.viw.registration.databinding.ActivityHomeBinding
//import com.viw.registration.fragments.AppointmentsFragment
//import com.viw.registration.fragments.EventsFragment
//import com.viw.registration.fragments.HomeFragment
//import com.viw.registration.fragments.MarketPlaceFragment
//import com.viw.registration.model.CardItem1
//import com.viw.registration.model.CardItem2
//import com.viw.registration.model.MenuItem
//class HomeActivity : AppCompatActivity() {
//
//    private lateinit var binding : ActivityHomeBinding
//    private lateinit var drawerMenuAdapter: DrawerMenuAdapter
//    private lateinit var cardItem1Adapter: CardItem1Adapter
//    private lateinit var cardItem2adapter: CardItem2Adapter
//    private val cardItem1List = mutableListOf<CardItem1>()
//    private val cardItem2List = mutableListOf<CardItem2>()
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityHomeBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        if (savedInstanceState == null) {
//            loadFragment(HomeFragment())
//        }
//
//
//        cardItem1Adapter = CardItem1Adapter(cardItem1List)
//        binding.recyclerMenu.layoutManager = LinearLayoutManager(requireContext())
//        binding.recyclerMenu.adapter = cardItem1Adapter
//
//        binding.bottomNavigation.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.nav_home -> loadFragment(HomeFragment())
//                R.id.nav_appointments -> loadFragment(AppointmentsFragment())
//                R.id.nav_events -> loadFragment(EventsFragment())
//                R.id.nav_marketplace -> loadFragment(MarketPlaceFragment())
//            }
//            true
//        }
//
//        binding.menuIcon.setOnClickListener {
//            if (!binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
//                binding.drawerLayout.openDrawer(GravityCompat.START)
//            } else {
//                binding.drawerLayout.closeDrawer(GravityCompat.START) // Optional: Close if already open
//            }
//        }
//
//        val menu = listOf(
//            MenuItem("My Profile" , R.drawable.drawer_profile ),
//            MenuItem("My Appointments", R.drawable.drawer_appointments),
//            MenuItem("Medical Records", R.drawable.drawer_medical),
//            MenuItem("Consultations", R.drawable.drawer_consultations),
//            MenuItem("Saved Doctors", R.drawable.drawer_doctors),
//            MenuItem("My Event", R.drawable.drawer_doctors),
//            MenuItem("Terms and Conditions", R.drawable.drawer_termsconditions),
//            MenuItem("Privacy Policy", R.drawable.drawer_policy),
//            MenuItem("Help & FAQs", R.drawable.drawer_helps),
//            MenuItem("Sign Out", R.drawable.drawer_signout)
//        )
//
//        drawerMenuAdapter = DrawerMenuAdapter(menu) { menuItem -> binding.drawerLayout.closeDrawers()
//        }
//
//        binding.recyclerMenu.layoutManager = LinearLayoutManager(this)
//        binding.recyclerMenu.adapter = drawerMenuAdapter
//    }
//
//    override fun onBackPressed() {
//        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            binding.drawerLayout.closeDrawer(GravityCompat.START)
//        } else {
//            super.onBackPressed()
//        }
//    }
//
//    private fun loadFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragme_container, fragment)
//            .commit()
//    }
//
//}
//
//
