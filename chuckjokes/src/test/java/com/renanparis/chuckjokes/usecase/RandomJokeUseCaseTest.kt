package com.renanparis.chuckjokes.usecase

import com.renanparis.chuckjokes.TestHelper
import com.renanparis.chuckjokes.data.api.JokeWebClient
import com.renanparis.chuckjokes.data.model.Joke
import com.renanparis.chuckjokes.utils.Resource
import com.renanparis.chuckjokes.utils.Status
import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class RandomJokeUseCaseTest {

    private val jokeWebClient = mockkClass(JokeWebClient()::class)
    private lateinit var result: Resource<Any>
    private val mockJoke = TestHelper().getRandomJoke()
    private lateinit var joke: Joke

    @Test
    fun should_returnSuccessData_WhenCallApiSuccess() {

        coEvery { jokeWebClient.getRandomJoke(CATEGORY)} returns mockJoke
        runBlocking {
            try {
                joke = jokeWebClient.getRandomJoke(CATEGORY)
                result = Resource.success(data = jokeWebClient.getRandomJoke(CATEGORY))

            } catch (e: Exception) {
                result = Resource.error(data = null, message = "Exception")
            }

            Assert.assertEquals(joke.id, ID)
            Assert.assertEquals(result.status, Status.SUCCESS)
            Assert.assertEquals(joke, result.data)
            Assert.assertNotEquals("Exception", result.message)
        }
    }

    @Test
    fun should_returnErrorData_WhenCallApiFailure() {

        coEvery { jokeWebClient.getCategories() } throws Exception(EXCEPTION_MESSAGE)
        runBlocking {
            result = try {
                Resource.success(data = jokeWebClient.getRandomJoke(CATEGORY))

            } catch (e: Exception) {
                Resource.error(data = null, message = EXCEPTION_MESSAGE)
            }
            Assert.assertEquals(result.data, null)
            Assert.assertEquals(result.status, Status.ERROR)
            Assert.assertEquals( EXCEPTION_MESSAGE, result.message)
        }
    }

    companion object {
        const val CATEGORY = "travel"
        const val ID = "9rqqcar_s5mqpujm0yu5za"
        const val EXCEPTION_MESSAGE = "Error Api"
    }

}