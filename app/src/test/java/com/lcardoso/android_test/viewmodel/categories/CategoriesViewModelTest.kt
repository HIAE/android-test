package com.lcardoso.android_test.viewmodel.categories

import com.lcardoso.android_test.viewmodel.categories.CategoriesViewModelRobot.act
import com.lcardoso.android_test.viewmodel.categories.CategoriesViewModelRobot.arrange
import com.lcardoso.android_test.viewmodel.categories.CategoriesViewModelRobot.assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CategoriesViewModelTest {

    @Test
    fun `when use case returns success response should expose success state`() {
        arrange {
            mockSuccessResponse()
        }
        act {
            fetchCategories()
        }
        assert {
            isStateSuccess()
        }
    }

    @Test
    fun `when use case returns error response should expose error state`() {
        arrange {
            mockErrorResponse()
        }
        act {
            fetchCategories()
        }
        assert {
            isStateError()
        }
    }
}