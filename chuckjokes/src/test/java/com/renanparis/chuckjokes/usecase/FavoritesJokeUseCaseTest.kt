package com.renanparis.chuckjokes.usecase

import com.renanparis.chuckjokes.TestHelper
import com.renanparis.chuckjokes.data.database.dao.JokeDao
import com.renanparis.chuckjokes.utils.Resource
import com.renanparis.chuckjokes.utils.Status
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class FavoritesJokeUseCaseTest {


    private val mockList = TestHelper().getJokes()
    private val dao = mockk<JokeDao>()
    private lateinit var result: Resource<Any>


    @Test
    fun should_returnSuccessData_WhenGetListDao() {

        coEvery { dao.getJokes()} returns mockList
        runBlocking {
            result = try {
                Resource.success(data = dao.getJokes())

            } catch (e: Exception) {
                Resource.error(data = null, message = "Exception")
            }

            Assert.assertEquals(mockList, result.data)
            Assert.assertEquals(result.status, Status.SUCCESS)
            Assert.assertNotEquals("Exception", result.message)
        }
    }

    @Test
    fun should_returnError_WhenGetListDaoFailure() {

        coEvery { dao.getJokes()} throws Exception(MESSAGE_ERROR)
        runBlocking {
            result = try {
                Resource.success(data = dao.getJokes())

            } catch (e: Exception) {
                Resource.error(data = null, message = MESSAGE_ERROR)
            }

            Assert.assertNotEquals(mockList, result.data)
            Assert.assertEquals(result.status, Status.ERROR)
            Assert.assertEquals(MESSAGE_ERROR, result.message)
        }
    }

    companion object {
        const val MESSAGE_ERROR = "Error"
    }
}