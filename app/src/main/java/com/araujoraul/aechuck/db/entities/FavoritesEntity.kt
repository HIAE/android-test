package com.araujoraul.aechuck.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class FavoritesEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var joke: String = "",
    var category: String = ""
) : Parcelable