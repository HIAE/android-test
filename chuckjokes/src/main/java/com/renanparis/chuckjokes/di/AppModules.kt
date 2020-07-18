package com.renanparis.chuckjokes.di

import com.renanparis.chuckjokes.data.api.JokeWebClient
import com.renanparis.chuckjokes.data.repository.JokeRepository
import com.renanparis.chuckjokes.ui.adapter.CategoriesAdapter
import com.renanparis.chuckjokes.ui.viewmodel.CategoryViewModel
import com.renanparis.chuckjokes.usecase.CategoryUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    factory<CategoriesAdapter> { CategoriesAdapter(get()) }
}

val dataModule = module {
    single<JokeWebClient> { JokeWebClient() }
    single<JokeRepository> { JokeRepository(get()) }
    single<CategoryUseCase> { CategoryUseCase(get()) }
}

val viewModelModule = module {
viewModel<CategoryViewModel>{ CategoryViewModel(get()) }
}