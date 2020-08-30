package com.araujoraul.aechuck.domain.model

import com.google.gson.annotations.SerializedName

data class ChuckJokes(
    @SerializedName("id") var id: String = "",
    @SerializedName("icon_url") var icon: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("value") var value: String = "",
    @SerializedName("created_at") var createdAt: String = "",
    @SerializedName("updated_at") var updatedAt: String = "",
    @SerializedName("categories") var categories: List<String>? = null
)