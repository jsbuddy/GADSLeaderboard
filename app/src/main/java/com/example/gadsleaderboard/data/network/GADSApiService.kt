package com.example.gadsleaderboard.data.network

import com.example.gadsleaderboard.data.models.LearningLeader
import com.example.gadsleaderboard.data.models.SkillIQLeader
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface GADSApiService {
    @GET("hours")
    suspend fun getLearningLeaders(): Response<List<LearningLeader>>

    @GET("skilliq")
    suspend fun getSkillIQLeaders(): Response<List<SkillIQLeader>>

    @POST
    @FormUrlEncoded
    suspend fun submitProject(
        @Field("entry.1824927963") email: String,
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.284483984") githubLink: String,
        @Url url: String? = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse",
    ): Response<ResponseBody>

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
