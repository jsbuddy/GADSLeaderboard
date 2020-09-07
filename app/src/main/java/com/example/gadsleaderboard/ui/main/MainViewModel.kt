package com.example.gadsleaderboard.ui.main

import androidx.lifecycle.*
import com.example.gadsleaderboard.data.models.LearningLeader
import com.example.gadsleaderboard.data.models.SkillIQLeader
import com.example.gadsleaderboard.data.repositories.LeaderRepository
import com.example.gadsleaderboard.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: LeaderRepository) : ViewModel() {

    val learningLeaders = MutableLiveData<Resource<List<LearningLeader>>>()
    val skillIQLeaders = MutableLiveData<Resource<List<SkillIQLeader>>>()

    init {
        getLearningLeaders()
        getSkillIQLeaders()
    }

    private fun getLearningLeaders() = viewModelScope.launch {
        learningLeaders.postValue(Resource.Loading())
        val result = repository.getLearningLeaders()
        learningLeaders.postValue(handleResponse(result))
    }

    private fun getSkillIQLeaders() = viewModelScope.launch {
        skillIQLeaders.postValue(Resource.Loading())
        val result = repository.getSkillIQLeaders()
        skillIQLeaders.postValue(handleResponse(result))
    }

    private fun <T> handleResponse(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}