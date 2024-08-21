package com.example.amphabians.data

import com.example.amphabians.model.AmphibiansPhotos
import com.example.amphabians.network.AmphibiansApiService

interface AmphibiansPhotoRepository {
       suspend fun getList():List<AmphibiansPhotos>
}

class ImpAmphibiansPhotoRepository(
    private val amphibiansApiService: AmphibiansApiService
):AmphibiansPhotoRepository{
     override suspend fun getList():List<AmphibiansPhotos> = amphibiansApiService.getData()
}