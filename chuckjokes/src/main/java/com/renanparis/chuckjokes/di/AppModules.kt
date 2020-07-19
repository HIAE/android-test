package com.renanparis.chuckjokes.di

import androidx.room.Room
import com.renanparis.chuckjokes.data.api.JokeWebClient
import com.renanparis.chuckjokes.data.database.AppDataBase
import com.renanparis.chuckjokes.data.database.dao.JokeDao
import com.renanparis.chuckjokes.data.repository.JokeRepository
import com.renanparis.chuckjokes.ui.adapter.CategoriesAdapter
import com.renanparis.chuckjokes.ui.adapter.FavoritesJokesAdapter
import com.renanparis.chuckjokes.ui.viewmodel.CategoryViewModel
import com.renanparis.chuckjokes.ui.viewmodel.FavoritesJokesViewModel
import com.renanparis.chuckjokes.ui.viewmodel.RandomJokeViewModel
import com.renanparis.chuckjokes.usecase.CategoryUseCase
import com.renanparis.chuckjokes.usecase.FavoritesJokeUseCase
import com.renanparis.chuckjokes.usecase.RandomJokeUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val NAME_DATABASE = "jokes.db"

val uiModule = module {
    factory<CategoriesAdapter> { CategoriesAdapter(get()) }
    factory<FavoritesJokesAdapter> { FavoritesJokesAdapter(get()) }
}

val dataModule = module {
    single<JokeWebClient> { JokeWebClient() }
    single<JokeRepository> { JokeRepository(get(), get()) }
    single<CategoryUseCase> { CategoryUseCase(get()) }
    single<RandomJokeUseCase> { RandomJokeUseCase(get()) }
    single<FavoritesJokeUseCase> { FavoritesJokeUseCase(get()) }
    single<JokeDao> { get<AppDataBase>().jokeDao() }
    single<AppDataBase> {
        Room.databaseBuilder(
                get(),
                AppDataBase::class.java,
                NAME_DATABASE
        ).build()
    }

}

val viewModelModule = module {
    viewModel<CategoryViewModel> { CategoryViewModel(get()) }
    viewModel<RandomJokeViewModel> { RandomJokeViewModel(get()) }
    viewModel<FavoritesJokesViewModel> { FavoritesJokesViewModel(get()) }
}