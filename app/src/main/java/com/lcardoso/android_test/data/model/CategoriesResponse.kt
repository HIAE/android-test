package com.lcardoso.android_test.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoriesResponse(
    @SerializedName("") val categories: List<String>? = null
) : Parcelable