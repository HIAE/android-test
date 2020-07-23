package com.lcardoso.android_test.di

import com.lcardoso.android_test.api.ChuckNorrisAPI
import com.lcardoso.android_test.api.RetrofitProvider
import com.lcardoso.android_test.data.JokesRepositoryImp
import com.lcardoso.android_test.ui.categories.CategoriesViewModel
import com.lcardoso.android_test.ui.joke.JokeViewModel
import com.lcardoso.android_test.usecase.FetchCategoriesUseCase
import com.lcardoso.android_test.usecase.FetchJokesUseCase
import com.lcardoso.android_test.usecase.FetchPreviousJokeUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ChuckNorrisComponent {

    val apiModule = module {
        fun provideRetrofit() =
            RetrofitProvider.providesRetrofitApi(ChuckNorrisAPI::class.java)
        single { provideRetrofit() }
    }

    val repositoryModule = module {
        single { JokesRepositoryImp(get()) }
    }

    val useCaseModule = module {
        single { FetchCategoriesUseCase(get()) }
        single { FetchJokesUseCase(get()) }
        single { FetchPreviousJokeUseCase(get()) }
    }

    val viewModelModule = module {
        viewModel { CategoriesViewModel(get()) }
        viewModel { JokeViewModel(get(), get()) }
    }
}