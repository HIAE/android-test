package com.lcardoso.android_test.usecase.categories

import com.lcardoso.android_test.data.JokesRepositoryImp
import com.lcardoso.android_test.data.model.CategoriesVO
import com.lcardoso.android_test.usecase.FetchCategoriesUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import java.lang.RuntimeException

object FetchCategoriesUseCaseRobot {

    private val repository = mockk<JokesRepositoryImp>()
    private val subject = FetchCategoriesUseCase(repository)
    private lateinit var testObserver: TestObserver<CategoriesVO>

    class FetchCategoriesUseCaseRobotArrange {
        private fun mockCategories(): CategoriesVO = CategoriesVO(
            categories = listOf("dev", "animal", "food")
        )

        fun mockFetchCategoriesSuccess() {
            every {
                repository.fetchCategories()
            } returns Single.just(mockCategories())
        }

        fun mockFetchCategoriesError() {
            every {
                repository.fetchCategories()
            } returns Single.error(RuntimeException())
        }
    }

    class FetchCategoriesUseCaseRobotAct {
        fun execute() {
            testObserver = TestObserver()
            val result = subject.execute()
            result.subscribe(testObserver)
        }
    }

    class FetchCategoriesUseCaseRobotAssert {
        fun isSuccess() {
            assert(testObserver.values().isNotEmpty())
        }

        fun isError() {
            assert(testObserver.errors().isNotEmpty())
        }
    }

    fun arrange(func: FetchCategoriesUseCaseRobotArrange.() -> Unit) =
        FetchCategoriesUseCaseRobotArrange().apply(func)

    fun act(func: FetchCategoriesUseCaseRobotAct.() -> Unit) =
        FetchCategoriesUseCaseRobotAct().apply(func)

    fun assert(func: FetchCategoriesUseCaseRobotAssert.() -> Unit) =
        FetchCategoriesUseCaseRobotAssert().apply(func)
}