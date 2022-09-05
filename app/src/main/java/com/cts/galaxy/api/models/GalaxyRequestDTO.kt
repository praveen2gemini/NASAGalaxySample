package com.cts.galaxy.api.models

data class GalaxyRequestDTO(
    val searchKey: String,
    val mediaType: String,
    val yearStart: String,
    val yearEnd: String
)
