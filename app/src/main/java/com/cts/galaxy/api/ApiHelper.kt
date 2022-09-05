package com.cts.galaxy.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getGalaxyCollection(
        searchKey: String,
        mediaType: String,
        yearStart: String,
        yearEnd: String
    ) = apiService.getGalaxyCollection(searchKey, mediaType, yearStart, yearEnd)
}