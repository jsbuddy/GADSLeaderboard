package com.example.gadsleaderboard.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gadsleaderboard.R
import com.example.gadsleaderboard.data.repositories.LeaderRepository
import com.example.gadsleaderboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var model: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = LeaderRepository()
        val factory = MainVewModelProviderFactory(repository)
        model = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }
}