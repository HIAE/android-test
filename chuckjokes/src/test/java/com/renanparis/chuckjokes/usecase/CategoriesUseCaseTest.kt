package com.renanparis.chuckjokes.usecase

import com.renanparis.chuckjokes.TestHelper
import com.renanparis.chuckjokes.data.api.JokeWebClient
import com.renanparis.chuckjokes.utils.Resource
import com.renanparis.chuckjokes.utils.Status
import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CategoriesUseCaseTest {

    private val jokeWebClient = mockkClass(JokeWebClient()::class)
    private lateinit var result: Resource<Any>
    private val mockList = TestHelper().getCategoriesList()

    @Test
    fun should_returnSuccessData_WhenCallApiSuccess() {

        coEvery { jokeWebClient.getCategories() } returns mockList
        var list = emptyList<String>()
        runBlocking {
            try {
                list = jokeWebClient.getCategories()
                result = Resource.success(data = jokeWebClient.getCategories())

            } catch (e: Exception) {
                result = Resource.error(data = null, message = "Exception")
            }

            Assert.assertTrue(list.isNotEmpty())
            Assert.assertEquals(3, jokeWebClient.getCategories().size)
            Assert.assertEquals(result.status, Status.SUCCESS)
            Assert.assertEquals(mockList, result.data)
            Assert.assertNotEquals("Exception", result.message)
        }
    }

    @Test
    fun should_returnErrorData_WhenCallApiFailure() {

        coEvery { jokeWebClient.getCategories() } throws Exception("Error Api")
        var list = emptyList<String>()
        runBlocking {
            try {
                list = jokeWebClient.getCategories()
                result = Resource.success(data = jokeWebClient.getCategories())

            } catch (e: Exception) {
                result = Resource.error(data = null, message = e.message.toString())
            }
            Assert.assertTrue(list.isEmpty())
            Assert.assertEquals(result.status, Status.ERROR)
            Assert.assertNotEquals(mockList, result.data)
            Assert.assertEquals("Error Api", result.message)
        }
    }
}