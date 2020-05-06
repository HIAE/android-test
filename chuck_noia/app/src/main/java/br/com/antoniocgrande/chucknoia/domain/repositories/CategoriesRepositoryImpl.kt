package br.com.antoniocgrande.chucknoia.domain.repositories

import android.os.SystemClock
import br.com.antoniocgrande.chucknoia.data.repositories.CategoriesRepository
import io.reactivex.rxjava3.core.Observable

/* Copyright 2020.
 ************************************************************
 * Project     : ChuckNoia
 * Description : br.com.antoniocgrande.chucknoia.domain.repositories
 * Created by  : antoniocgrande
 * Date        : 06/05/2020 16:34
 ************************************************************/
class CategoriesRepositoryImpl : CategoriesRepository {

    override fun listCategories(): Observable<List<String>> {
        return Observable.defer {
            SystemClock.sleep(5000)
            Observable
                .just(
                    listOf(
                        "animal",
                        "career",
                        "celebrity",
                        "dev",
                        "explicit",
                        "fashion",
                        "food",
                        "history",
                        "money",
                        "movie",
                        "music",
                        "political",
                        "religion",
                        "science",
                        "sport",
                        "travel"
                    )
                )
                .concatMapIterable { item -> listOf(item) }
        }
    }

}