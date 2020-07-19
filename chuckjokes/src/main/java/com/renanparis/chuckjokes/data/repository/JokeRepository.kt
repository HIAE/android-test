package com.renanparis.chuckjokes.data.repository

import com.renanparis.chuckjokes.data.api.JokeWebClient
import com.renanparis.chuckjokes.data.database.dao.JokeDao
import com.renanparis.chuckjokes.data.model.Joke

class JokeRepository(private val jokeWebClient: JokeWebClient,
                     private val dao: JokeDao) {

    suspend fun getCategories() = jokeWebClient.getCategories()

    suspend fun getRandomJoke(category: String) = jokeWebClient.getRandomJoke(category)

    suspend fun deleteJoke(joke: Joke)  = dao.removeJoke(joke)

    suspend fun saveJoke(joke: Joke) = dao.saveJoke(joke)

}