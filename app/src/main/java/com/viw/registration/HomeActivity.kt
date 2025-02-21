package com.viw.registration

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.viw.registration.adapter.DrawerMenuAdapter
import com.viw.registration.fragments.AppointmentsFragment
import com.viw.registration.fragments.EventsFragment
import com.viw.registration.fragments.HomeFragment
import com.viw.registration.fragments.MarketPlaceFragment
import com.viw.registration.model.MenuItem
class HomeActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DrawerMenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        drawerLayout = findViewById(R.id.drawer_layout)
        recyclerView = findViewById(R.id.recycler_menu)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        val menuIcon = findViewById<ImageView>(R.id.menuIcon)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_appointments -> loadFragment(AppointmentsFragment())
                R.id.nav_events -> loadFragment(EventsFragment())
                R.id.nav_marketplace -> loadFragment(MarketPlaceFragment())
            }
            true
        }

        menuIcon.setOnClickListener {
            if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.openDrawer(GravityCompat.START)
            } else {
                drawerLayout.closeDrawer(GravityCompat.START) // Optional: Close if already open
            }
        }


        val menu = listOf(
            MenuItem("My Profile" , R.drawable.drawer_profile ),
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

        adapter = DrawerMenuAdapter(menu) { menuItem ->
            drawerLayout.closeDrawers()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragme_container, fragment)
            .commit()
    }

}


