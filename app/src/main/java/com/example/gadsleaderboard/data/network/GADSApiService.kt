package com.example.gadsleaderboard.data.network

import com.example.gadsleaderboard.data.models.LearningLeader
import com.example.gadsleaderboard.data.models.SkillIQLeader
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GADSApiService {
    @GET("hours")
    suspend fun getLearningLeaders(): Response<List<LearningLeader>>

    @GET("skilliq")
    suspend fun getSkillIQLeaders(): Response<List<SkillIQLeader>>

    companion object {
        private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        operator fun invoke(): GADSApiService {
            val okHttp = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder().client(okHttp)
                .baseUrl("https://gadsapi.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(GADSApiService::class.java)
        }
    }
}
