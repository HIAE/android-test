package com.renanparis.chuckjokes

import com.google.gson.Gson
import com.renanparis.chuckjokes.data.model.Joke

class TestHelper {

    fun getCategoriesList(): List<String> {

        return listOf("test1", "test2", "test3")
    }

    fun getRandomJoke(): Joke {
        val jsonJoke = "{\"categories\":[\"travel\"],\"created_at\":\"2020-01-05 13:42:19.576875\",\"icon_url\":\"https://assets.chucknorris.host/img/avatar/chuck-norris.png\",\"id\":\"9rqqcar_s5mqpujm0yu5za\",\"updated_at\":\"2020-01-05 13:42:19.576875\",\"url\":\"https://api.chucknorris.io/jokes/9rqqcar_s5mqpujm0yu5za\",\"value\":\"For Spring Break '05, Chuck Norris drove to Madagascar, riding a chariot pulled by two electric eels.\"}"
        return Gson().fromJson(jsonJoke, Joke::class.java)
    }

    fun getJokes(): List<Joke> {
        val joke = getRandomJoke()
        val list = mutableListOf<Joke>()

        for (i in 1..5) {
            list.add(joke)
        }
        return list
    }
}