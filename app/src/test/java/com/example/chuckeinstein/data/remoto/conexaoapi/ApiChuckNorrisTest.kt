package com.example.chuckeinstein.data.remoto.conexaoapi


import com.example.chuckeinstein.data.remoto.models.DetalhesPiada
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection

class ApiChuckNorrisTest {

    private val serverMockado = MockWebServer()
    private lateinit var apiChuckNorris: ApiChuckNorris
    private val categoria_1 = "categoria1"
    private val categoria_2 = "categoria2"
    private val categoria_3 = "categoria3"
    private val data_criacao = "2020-01-05 13:42:18.823766"
    private val url_icone = "https://url.com/chuck-norris.png"
    private val id_piada = "0wdewlp2tz-mt_upesvrjw"
    private val data_atualizacoa = "2020-01-05 13:42:18.823766"
    private val url_piada = "https://url.da.piada"
    private val texto_piada = "Texto da piada"
    private val jsonCategorias = "[\"$categoria_1\",\"$categoria_2\",\"$categoria_3\"]"
    private val jsonDetalhesCategoria =
        "{\"categories\":[\"$categoria_1\"],\"created_at\":\"$data_criacao\",\"" +
                "icon_url\":\"$url_icone\",\"id\":\"$id_piada\",\"" +
                "updated_at\":\"$data_atualizacoa\",\"url\":\"$url_piada\",\"value\":\"$texto_piada\"}"
    private val listaCategoriasApi = listOf(categoria_1, categoria_2, categoria_3)
    private val detalhesPiada = DetalhesPiada(
        categories = listOf(categoria_1),
        created_at = data_criacao,
        icon_url = url_icone,
        id = id_piada,
        updated_at = data_atualizacoa,
        url = url_piada,
        value = texto_piada
    )

    @Before
    fun antesTeste() {
        serverMockado.start()
        apiChuckNorris = Retrofit.Builder()
            .baseUrl(serverMockado.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiChuckNorris::class.java)
    }

    @After
    fun depoisTeste() {
        serverMockado.shutdown()
    }

    @Test
    fun `json externo categorias deve retornar lista de strings com categorias`() = runBlocking {
        val resposta = MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(jsonCategorias)
        serverMockado.enqueue(resposta)
        assertEquals(apiChuckNorris.getCategorias(), listaCategoriasApi)
    }

    @Test
    fun `json piada aleatoria por categoria deve retornar objeto de detalhes piada`() =
        runBlocking {
            val resposta = MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(jsonDetalhesCategoria)
            serverMockado.enqueue(resposta)
            assertEquals(apiChuckNorris.getPiadaAleatoriaPorCategoria(categoria_1), detalhesPiada)
        }
}