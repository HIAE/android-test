package br.com.antoniocgrande.chucknoia.domain.repositories

import android.os.SystemClock
import br.com.antoniocgrande.chucknoia.data.model.Category
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

    private val categoriesMocked = mutableListOf<Category>()

    override fun listCategories(): Observable<MutableList<Category>> {
        categoriesMocked.add(Category("animal"))
        categoriesMocked.add(Category("career"))
        categoriesMocked.add(Category("celebrity"))
        categoriesMocked.add(Category("dev"))
        categoriesMocked.add(Category("explicit"))
        categoriesMocked.add(Category("fashion"))
        categoriesMocked.add(Category("food"))
        categoriesMocked.add(Category("history"))
        categoriesMocked.add(Category("money"))
        categoriesMocked.add(Category("movie"))
        categoriesMocked.add(Category("music"))
        categoriesMocked.add(Category("political"))
        categoriesMocked.add(Category("religion"))
        categoriesMocked.add(Category("science"))
        categoriesMocked.add(Category("sport"))
        categoriesMocked.add(Category("travel"))

        return Observable.defer {
            SystemClock.sleep(5000)
            Observable
                .just(categoriesMocked)
                .concatMapIterable { item -> mutableListOf(item) }
        }
    }

}