package com.example.gadsleaderboard.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gadsleaderboard.adapters.SectionsPagerAdapter
import com.example.gadsleaderboard.data.repositories.LeaderRepository
import com.example.gadsleaderboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var model: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPage()

        val repository = LeaderRepository()
        val factory = MainVewModelProviderFactory(repository)
        model = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    private fun setupViewPage() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }
}