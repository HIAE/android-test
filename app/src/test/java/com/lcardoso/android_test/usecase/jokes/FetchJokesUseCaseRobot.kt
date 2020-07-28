package com.lcardoso.android_test.usecase.jokes

import com.lcardoso.android_test.data.JokesRepositoryImp
import com.lcardoso.android_test.data.model.JokeVO
import com.lcardoso.android_test.usecase.FetchJokesUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.observers.TestObserver

object FetchJokesUseCaseRobot {

    private val repository = mockk<JokesRepositoryImp>()
    private val subject =
        FetchJokesUseCase(repository)
    private lateinit var testObserver: TestObserver<JokeVO>

    class FetchJokesUseCaseRobotArrange {
        private fun mockJokes(): JokeVO = JokeVO(
            id = "26072020",
            category = "funny",
            joke = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        )

        fun mockFetchJokesSuccess() {
            every {
                repository.fetchJokes(any())
            } returns Single.just(mockJokes())
        }

        fun mockFetchJokesError() {
            every {
                repository.fetchJokes(any())
            } returns Single.error(RuntimeException())
        }
    }

    class FetchJokesUseCaseRobotAct {
        fun execute() {
            testObserver = TestObserver()
            val result = subject.execute(
                FetchJokesUseCase.Param(
                    category = "any"
                )
            )
            result.subscribe(testObserver)
        }
    }

    class FetchJokesUseCaseRobotAssert {
        fun isSuccess() {
            assert(testObserver.values().isNotEmpty())
        }

        fun isError() {
            assert(testObserver.errors().isNotEmpty())
        }
    }

    fun arrange(func: FetchJokesUseCaseRobotArrange.() -> Unit) =
        FetchJokesUseCaseRobotArrange().apply(func)

    fun act(func: FetchJokesUseCaseRobotAct.() -> Unit) =
        FetchJokesUseCaseRobotAct().apply(func)

    fun assert(func: FetchJokesUseCaseRobotAssert.() -> Unit) =
        FetchJokesUseCaseRobotAssert().apply(func)
}