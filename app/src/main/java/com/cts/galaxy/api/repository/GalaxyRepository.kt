package com.cts.galaxy.api.repository

import com.cts.galaxy.api.models.GalaxyDTO
import com.cts.galaxy.api.models.GalaxyRequestDTO

interface GalaxyRepository {

    suspend fun fetchGalaxyCollection(
        requestDTO: GalaxyRequestDTO
    ): GalaxyDTO
}