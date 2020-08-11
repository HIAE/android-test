package com.wesleyerick.chuckjokes.entity

import com.google.gson.annotations.SerializedName

data class MessageDetails(
//    @SerializedName("categories")
//    var categories: Array<>,

    @SerializedName("created_at")
    var created_at: String,

    @SerializedName("icon_url")
    var icon_url: String,

    @SerializedName("id")
    var id: String,

    @SerializedName("updated_at")
    var updated_at: String,

    @SerializedName("url")
    var url: String,

    @SerializedName("value")
    var value: String
)