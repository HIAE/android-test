package br.com.antoniocgrande.chucknoia

import android.app.Application
import br.com.antoniocgrande.chucknoia.di.Module
import org.koin.android.ext.android.startKoin

/* Copyright 2020.
 ************************************************************
 * Project     : ChuckNoia
 * Description : br.com.antoniocgrande.chucknoia
 * Created by  : antoniocgrande
 * Date        : 11/05/2020 09:59
 ************************************************************/
class ChuckApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(Module.module))
    }

}