package com.renanparis.chuckjokes.data.model

import com.google.gson.annotations.Expose

data class Joke(
        val categories: List<String> = mutableListOf(),
        val created_at: String = "",
        val icon_url: String = "",
        val id: String = "",
        val updated_at: String = "",
        val url: String = "",
        val value: String = "",
        @Expose
        var favorite: Boolean = false
) {
}