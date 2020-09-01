package com.lcardoso.android_test.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jokes")
data class JokeEntity(
    @PrimaryKey
    val id: String,
    val category: String,
    val joke: String
)