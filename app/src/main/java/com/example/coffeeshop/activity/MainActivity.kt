package com.example.coffeeshop.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshop.R
import com.example.coffeeshop.adapter.CategoryAdapter
import com.example.coffeeshop.adapter.OffersAdapter
import com.example.coffeeshop.adapter.PopularAdapter
import com.example.coffeeshop.databinding.ActivityMainBinding
import com.example.coffeeshop.fragment.GiftFragment
import com.example.coffeeshop.fragment.ProfileFragment
import com.example.coffeeshop.fragment.SettingsFragment
import com.example.coffeeshop.fragment.MainFragment
import com.example.coffeeshop.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bottomNavigation.background = null


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment())
                .commit()
        }

        setupBottomNavigation()
        bottomMenu()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    replaceFragment(MainFragment())
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                R.id.nav_offer -> {
                    replaceFragment(GiftFragment())
                    true
                }
                R.id.nav_settings -> {
                    replaceFragment(SettingsFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun bottomMenu() {
        binding.cartBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, CartActivity::class.java)
            startActivity(intent)
        }
    }
}

