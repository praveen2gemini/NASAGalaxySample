package com.cts.galaxy.api.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * @author Praveen Kumar Sugumaran
 */
@Parcelize
data class Items(
    @SerializedName("href") val href: String,
    @SerializedName("data") val data: List<Data>,
    @SerializedName("links") val links: List<Links>
) : Parcelable {

    override fun hashCode(): Int {
        return href.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Items

        if (href != other.href) return false
        if (data != other.data) return false
        if (links != other.links) return false

        return true
    }
}