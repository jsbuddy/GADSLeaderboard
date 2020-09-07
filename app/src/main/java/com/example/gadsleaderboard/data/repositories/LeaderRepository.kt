package com.example.gadsleaderboard.data.repositories

import com.example.gadsleaderboard.data.network.GADSApiService

class LeaderRepository {
    suspend fun getLearningLeaders() = GADSApiService().getLearningLeaders()

    suspend fun getSkillIQLeaders() = GADSApiService().getSkillIQLeaders()
}
