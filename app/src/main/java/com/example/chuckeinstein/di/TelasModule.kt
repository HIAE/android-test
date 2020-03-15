package com.example.chuckeinstein.di

import com.example.chuckeinstein.MainActivity
import com.example.chuckeinstein.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TelasModule {
    @ContributesAndroidInjector
    abstract fun injetarMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun injetarMainFragment(): MainFragment
}
