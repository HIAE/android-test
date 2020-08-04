package com.renanparis.chuckjokes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.renanparis.chuckjokes.data.database.converter.ListStringConverter

@Entity
data class Joke(
        val categories: List<String> = mutableListOf(),
        val created_at: String = "",
        val icon_url: String = "",
        @PrimaryKey
        val id: String = "",
        val updated_at: String = "",
        val url: String = "",
        val value: String = "",
        @Expose
        var favorite: Boolean = false
) {
}