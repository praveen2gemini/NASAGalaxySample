package com.cts.galaxy.api.models

import com.google.gson.annotations.SerializedName

data class GalaxyDTO(
    @SerializedName("collection") val collection: NasaCollection
)