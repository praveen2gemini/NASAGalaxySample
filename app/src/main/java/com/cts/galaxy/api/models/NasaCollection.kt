package com.cts.galaxy.api.models

import com.google.gson.annotations.SerializedName

/**
 * @author Praveen Kumar Sugumaran
 */
data class NasaCollection(

    @SerializedName("version") val version: Double,
    @SerializedName("href") val href: String,
    @SerializedName("items") val items: List<Items>,
    @SerializedName("metadata") val metadata: Metadata
)