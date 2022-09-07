package com.cts.galaxy.api.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * @author Praveen Kumar Sugumaran
 */
@Parcelize
data class Links(
    @SerializedName("href") val href: String,
    @SerializedName("rel") val rel: String,
    @SerializedName("render") val render: String
) : Parcelable