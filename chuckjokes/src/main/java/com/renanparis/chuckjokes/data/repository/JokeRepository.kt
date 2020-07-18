package com.renanparis.chuckjokes.data.repository

import com.renanparis.chuckjokes.data.api.JokeWebClient

class JokeRepository(private val jokeWebClient: JokeWebClient) {

    suspend fun getCategories() = jokeWebClient.getCategories()
}