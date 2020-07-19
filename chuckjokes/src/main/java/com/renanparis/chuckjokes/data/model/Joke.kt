package com.renanparis.chuckjokes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

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