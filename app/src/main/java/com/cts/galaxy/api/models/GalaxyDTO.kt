package com.cts.galaxy.api.models

import com.google.gson.annotations.SerializedName

/**
 * @author Praveen Kumar Sugumaran
 */
data class GalaxyDTO(
    @SerializedName("collection") val collection: NasaCollection
)