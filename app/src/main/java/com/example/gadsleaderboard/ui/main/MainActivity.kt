package com.example.gadsleaderboard.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
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
        setDefaultTheme()

        val repository = LeaderRepository()
        val factory = MainVewModelProviderFactory(repository)
        model = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    private fun setDefaultTheme() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val theme = pref.getString("theme", getString(R.string.pref_theme_default_value))!!

        val values = arrayOf(
            AppCompatDelegate.MODE_NIGHT_NO,
            AppCompatDelegate.MODE_NIGHT_YES,
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        )
        if (AppCompatDelegate.getDefaultNightMode() != values[theme.toInt()])
            AppCompatDelegate.setDefaultNightMode(values[theme.toInt()])
    }
}
