package com.example.chuckeinstein.di

import com.example.chuckeinstein.ui.ChuckEinsteinActivity
import com.example.chuckeinstein.ui.piadas.fragments.ListaCategoriasFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TelasModule {
    @ContributesAndroidInjector
    abstract fun injetarAtividadeChuckEinstein(): ChuckEinsteinActivity

    @ContributesAndroidInjector
    abstract fun injetarFragmentoListaCategorias(): ListaCategoriasFragment
}
