package com.cts.galaxy.api.models

import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("total_hits") val totalHits: Int
)