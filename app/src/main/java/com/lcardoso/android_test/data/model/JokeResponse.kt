package com.lcardoso.android_test.data.model


import com.google.gson.annotations.SerializedName

data class JokeResponse(
    @SerializedName("categories") val categories: List<String>? = null,
    @SerializedName("created_at") val createdAt: String? = null,
    @SerializedName("icon_url") val iconUrl: String? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("updated_at") val updatedAt: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("value") val value: String? = null
)