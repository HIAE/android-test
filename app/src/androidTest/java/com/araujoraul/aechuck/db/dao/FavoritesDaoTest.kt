package com.araujoraul.aechuck.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.araujoraul.aechuck.db.AppDatabase
import com.araujoraul.aechuck.db.entities.FavoritesEntity
import com.araujoraul.aechuck.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class FavoritesDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: FavoritesDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.favoritesDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertFavorite() = runBlockingTest {

        val favoriteItem = FavoritesEntity(1, "This is a joke.", "Explicit")
        dao.insertFavoriteIntoDatabase(favoriteItem)

        val allFavorites = dao.getAllFavoritesFromDatabase().getOrAwaitValue()

        assertThat(allFavorites).contains(favoriteItem)

    }

    @Test
    fun deleteFavoriteById() = runBlockingTest {

        val favoriteItem = FavoritesEntity(1, "This is a joke.", "Explicit")
        dao.insertFavoriteIntoDatabase(favoriteItem)
        dao.deleteFavoriteByIdFromDatabase(1)

        val allFavorites = dao.getAllFavoritesFromDatabase().getOrAwaitValue()

        assertThat(allFavorites).doesNotContain(favoriteItem)
    }

    @Test
    fun observeFavorites() = runBlockingTest {

        val favoriteItem1 = FavoritesEntity(1, "This is a joke 1.", "Explicit")
        val favoriteItem2 = FavoritesEntity(2, "This is a joke 2.", "Animal")
        val favoriteItem3 = FavoritesEntity(3, "This is a joke 3.", "Dev")
        dao.insertFavoriteIntoDatabase(favoriteItem1)
        dao.insertFavoriteIntoDatabase(favoriteItem2)
        dao.insertFavoriteIntoDatabase(favoriteItem3)

        val observedItems = dao.getAllFavoritesFromDatabase().getOrAwaitValue()

        assertThat(observedItems.size).isEqualTo(3)

    }

}