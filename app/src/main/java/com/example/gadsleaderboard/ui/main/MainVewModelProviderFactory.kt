package com.example.gadsleaderboard.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gadsleaderboard.data.repositories.LeaderRepository

@Suppress("UNCHECKED_CAST")
class MainVewModelProviderFactory(private val repository: LeaderRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}