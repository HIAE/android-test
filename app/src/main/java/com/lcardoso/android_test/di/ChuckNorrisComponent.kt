package com.lcardoso.android_test.di

import androidx.room.Room
import com.lcardoso.android_test.api.ChuckNorrisAPI
import com.lcardoso.android_test.api.RetrofitProvider
import com.lcardoso.android_test.data.JokesRepositoryImp
import com.lcardoso.android_test.data.database.ChuckDatabase
import com.lcardoso.android_test.ui.categories.CategoriesViewModel
import com.lcardoso.android_test.ui.joke.JokeViewModel
import com.lcardoso.android_test.usecase.FetchCategoriesUseCase
import com.lcardoso.android_test.usecase.FetchJokesUseCase
import com.lcardoso.android_test.usecase.FetchPreviousJokeUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ChuckNorrisComponent {

    val apiModule = module {
        fun provideRetrofit() =
            RetrofitProvider.providesRetrofitApi(ChuckNorrisAPI::class.java)
        single { provideRetrofit() }
    }

    val databaseModule = module {
        single {
            Room.databaseBuilder(androidApplication(), ChuckDatabase::class.java, "chuck-db")
                .build()
        }
        single { get<ChuckDatabase>().jokeDAO() }
    }

    val repositoryModule = module {
        single { JokesRepositoryImp(get(), get()) }
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