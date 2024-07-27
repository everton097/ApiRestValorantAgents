package com.example.restapi_dotahero.network

import com.example.restapi_dotahero.data.ApiResponse
import com.example.restapi_dotahero.data.DetailsApiResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://valorant-api.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface OpenValorantApiService {
    @GET("v1/agents")
    suspend fun getAgents(): ApiResponse
}

object OpenValorantApi {
    val retrofitService: OpenValorantApiService by lazy {
        retrofit.create(OpenValorantApiService::class.java)
    }
}

interface OpenValorantApiServiceDetails {
    @GET("v1/agents/{id}")
    suspend fun getDetailsAgents(@Path("id") agentId: String): DetailsApiResponse
}

object OpenValorantApiDetails {
    val retrofitService: OpenValorantApiServiceDetails by lazy {
        retrofit.create(OpenValorantApiServiceDetails::class.java)
    }
}
