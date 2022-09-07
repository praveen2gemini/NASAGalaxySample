package com.cts.galaxy.api.repository

import com.cts.galaxy.api.models.GalaxyDTO
import com.cts.galaxy.api.models.GalaxyRequestDTO

/**
 * @author Praveen Kumar Sugumaran
 */
interface GalaxyRepository {

    suspend fun fetchGalaxyCollection(
        requestDTO: GalaxyRequestDTO
    ): GalaxyDTO
}