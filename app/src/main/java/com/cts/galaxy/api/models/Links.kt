package com.cts.galaxy.api.models

import com.google.gson.annotations.SerializedName

data class Links(

    @SerializedName("href") val href: String,
    @SerializedName("rel") val rel: String,
    @SerializedName("render") val render: String
)