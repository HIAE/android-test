package com.example.chuckeinstein.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chuckeinstein.ui.piadas.PiadasViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ChaveViewModel(PiadasViewModel::class)
    abstract fun providesPiadasViewModel(piadasViewModel: PiadasViewModel): ViewModel
}