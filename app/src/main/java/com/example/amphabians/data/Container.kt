package com.example.amphabians.data

import com.example.amphabians.network.AmphibiansApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val amphibiansPhotoRepository:AmphibiansPhotoRepository
}

class DefaultAppContainer:AppContainer{
    private val baseUrl="https://android-kotlin-fun-mars-server.appspot.com/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()
    private val retrofitService: AmphibiansApiService by lazy{
          retrofit.create(AmphibiansApiService::class.java)
    }
    override val amphibiansPhotoRepository: AmphibiansPhotoRepository by lazy {
        ImpAmphibiansPhotoRepository(retrofitService)
    }
}