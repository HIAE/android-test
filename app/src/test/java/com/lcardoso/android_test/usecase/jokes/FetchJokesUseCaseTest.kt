package com.lcardoso.android_test.usecase.jokes

import com.lcardoso.android_test.usecase.jokes.FetchJokesUseCaseRobot.act
import com.lcardoso.android_test.usecase.jokes.FetchJokesUseCaseRobot.arrange
import com.lcardoso.android_test.usecase.jokes.FetchJokesUseCaseRobot.assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FetchJokesUseCaseTest {

    @Test
    fun `when execute with success response should expose joke VO`() {
        arrange {
            mockFetchJokesSuccess()
        }
        act {
            execute()
        }
        assert {
            isSuccess()
        }
    }

    @Test
    fun `when execute with error response should expose error`() {
        arrange {
            mockFetchJokesError()
        }
        act {
            execute()
        }
        assert {
            isError()
        }
    }
}