package com.example.amphabians.network

import com.example.amphabians.model.AmphibiansPhotos
import retrofit2.http.GET

interface AmphibiansApiService {

    @GET("amphibians")
    suspend fun getData():List<AmphibiansPhotos>
}