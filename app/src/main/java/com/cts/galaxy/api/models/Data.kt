package com.cts.galaxy.api.models

import com.google.gson.annotations.SerializedName

data class Data(

    @SerializedName("album") val album: List<String>,
    @SerializedName("center") val center: String,
    @SerializedName("title") val title: String,
    @SerializedName("keywords") val keywords: List<String>,
    @SerializedName("location") val location: String,
    @SerializedName("nasa_id") val nasaId: String,
    @SerializedName("date_created") val dateCreated: String,
    @SerializedName("media_type") val mediaType: String,
    @SerializedName("description") val description: String
)