package com.cts.galaxy.api.repository

import com.cts.galaxy.api.ApiHelper
import com.cts.galaxy.api.models.GalaxyDTO
import com.cts.galaxy.api.models.GalaxyRequestDTO

/**
 * @author Praveen Kumar Sugumaran
 */
class GalaxyRepositoryImpl(private val apiHelper: ApiHelper) : GalaxyRepository {
    override suspend fun fetchGalaxyCollection(
        requestDTO: GalaxyRequestDTO
    ): GalaxyDTO {
        return apiHelper.getGalaxyCollection(
            requestDTO.searchKey,
            requestDTO.mediaType,
            requestDTO.yearStart,
            requestDTO.yearEnd
        )
    }
}