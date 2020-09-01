package com.lcardoso.android_test.usecase.categories

import com.lcardoso.android_test.usecase.categories.FetchCategoriesUseCaseRobot.act
import com.lcardoso.android_test.usecase.categories.FetchCategoriesUseCaseRobot.arrange
import com.lcardoso.android_test.usecase.categories.FetchCategoriesUseCaseRobot.assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FetchCategoriesUseCaseTest {

    @Test
    fun `when execute with success response should expose categories VO`() {
        arrange {
            mockFetchCategoriesSuccess()
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
            mockFetchCategoriesError()
        }
        act {
            execute()
        }
        assert {
            isError()
        }
    }
}