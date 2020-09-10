package com.example.gadsleaderboard.data.repositories

import com.example.gadsleaderboard.data.network.GADSApiService

class LeaderRepository {
    suspend fun getLearningLeaders() = GADSApiService().getLearningLeaders()

    suspend fun getSkillIQLeaders() = GADSApiService().getSkillIQLeaders()

    suspend fun submitProject(
        email: String,
        firstName: String,
        lastName: String,
        githubLink: String
    ) = GADSApiService().submitProject(email, firstName, lastName, githubLink)
}
