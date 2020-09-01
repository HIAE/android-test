package com.lcardoso.android_test.viewmodel.categories

import androidx.lifecycle.Observer
import com.lcardoso.android_test.assertInstanceOf
import com.lcardoso.android_test.data.StateError
import com.lcardoso.android_test.data.StateResponse
import com.lcardoso.android_test.data.StateSuccess
import com.lcardoso.android_test.data.model.CategoriesVO
import com.lcardoso.android_test.ui.categories.CategoriesViewModel
import com.lcardoso.android_test.usecase.FetchCategoriesUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single

object CategoriesViewModelRobot {

    private val useCase = mockk<FetchCategoriesUseCase>()
    private val subject = CategoriesViewModel(useCase)
    private val observer = mockk<Observer<StateResponse<CategoriesVO>>>(relaxed = true)

    class CategoriesViewModelRobotArrange {
        private fun mockCategories(): CategoriesVO = CategoriesVO(
            categories = listOf("dev", "animal", "food")
        )

        fun mockSuccessResponse() {
            every {
                useCase.execute()
            } returns Single.just(mockCategories())
        }

        fun mockErrorResponse() {
            every {
                useCase.execute()
            } returns Single.error(RuntimeException())
        }
    }

    class CategoriesViewModelRobotAct {
        fun fetchCategories() {
            subject.categoriesLiveData.observeForever(
                observer
            )
            subject.fetchCategories()
            subject.categoriesLiveData.removeObserver(
                observer
            )
        }
    }

    class CategoriesViewModelRobotAssert {
        fun isStateSuccess() {
            subject.categoriesLiveData.value.assertInstanceOf(StateSuccess::class.java)
        }

        fun isStateError() {
            subject.categoriesLiveData.value.assertInstanceOf(StateError::class.java)
        }
    }

    fun arrange(func: CategoriesViewModelRobotArrange.() -> Unit) =
        CategoriesViewModelRobotArrange()
            .apply(func)

    fun act(func: CategoriesViewModelRobotAct.() -> Unit) =
        CategoriesViewModelRobotAct()
            .apply(func)

    fun assert(func: CategoriesViewModelRobotAssert.() -> Unit) =
        CategoriesViewModelRobotAssert()
            .apply(func)
}