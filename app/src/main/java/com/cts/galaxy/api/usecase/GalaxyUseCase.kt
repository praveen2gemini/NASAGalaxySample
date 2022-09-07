package com.cts.galaxy.api.usecase

import com.cts.galaxy.api.Resource
import com.cts.galaxy.api.models.GalaxyDTO
import com.cts.galaxy.api.models.GalaxyRequestDTO
import com.cts.galaxy.api.repository.GalaxyRepository

/**
 * @author Praveen Kumar Sugumaran
 */
class GalaxyUseCase(private val galaxyRepository: GalaxyRepository) :
    BaseUseCase<GalaxyDTO, GalaxyRequestDTO>() {
    override suspend fun run(params: GalaxyRequestDTO): Resource<GalaxyDTO> {
        return try {
            val collection = galaxyRepository.fetchGalaxyCollection(params)
            Resource.success(collection)
        } catch (exp: Exception) {
            Resource.error(null, exp.message.toString())
        }
    }
}