package com.lcardoso.android_test.data.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface JokeDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addFavoriteJoke(joke: JokeEntity): Completable

    @Query("SELECT * FROM jokes")
    fun fetchFavoriteJokes(): Single<List<JokeEntity>>

    @Query("SELECT * FROM jokes WHERE id= :id")
    fun isFavoriteJoke(id: String): Single<JokeEntity>

    @Delete
    fun removeFavoriteJoke(joke: JokeEntity): Completable
}