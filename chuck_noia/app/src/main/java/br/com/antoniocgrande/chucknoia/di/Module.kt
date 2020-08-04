package br.com.antoniocgrande.chucknoia.di

import br.com.antoniocgrande.chucknoia.presentation.viewmodel.ViewModel
import org.koin.dsl.module.module

/* Copyright 2020.
 ************************************************************
 * Project     : Notas
 * Description : br.com.antoniocgrande.notas.di
 * Created by  : antoniocgrande
 * Date        : 25/03/2020 23:21
 ************************************************************/
object Module {

    val module = module {

        single { ViewModel() }

    }

}