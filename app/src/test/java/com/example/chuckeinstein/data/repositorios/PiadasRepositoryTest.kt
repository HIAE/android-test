package com.example.chuckeinstein.data.repositorios

import com.example.chuckeinstein.data.remoto.conexaoapi.ApiChuckNorris
import com.example.chuckeinstein.data.remoto.models.ChuckNorrisCategorias
import com.example.chuckeinstein.data.remoto.models.DetalhesPiada
import com.example.chuckeinstein.utils.Resource
import com.example.chuckeinstein.utils.ResponseHandler


import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PiadasRepositoryTest {

    private lateinit var repositorio: PiadasRepository
    private lateinit var responseHandler: ResponseHandler
    private lateinit var apiChuckNorris: ApiChuckNorris
    private lateinit var listaCategoriasApi: List<String>
    private lateinit var detalhesPiada: DetalhesPiada
    private val categoria_1 = "categoria 1"
    private val categoria_2 = "categoria 2"
    private val data_criacao = "2020-01-05 13:42:18.823766"
    private val url_icone = "https://assets.chucknorris.host/img/avatar/chuck-norris.png"
    private val id_piada = "0wdewlp2tz-mt_upesvrjw"
    private val data_atualizacoa = "2020-01-05 13:42:18.823766"
    private val url_piada = "https://api.chucknorris.io/jokes/0wdewlp2tz-mt_upesvrjw"
    private val texto_piada =
        "Chuck Norris does not follow fashion trends, they follow him. But then he turns around and kicks their ass. Nobody follows Chuck Norris."
    private lateinit var listaCategoriasObjeto: List<ChuckNorrisCategorias>
    private lateinit var respostaCategoriasAposSerTratada: Resource<List<ChuckNorrisCategorias>>
    private lateinit var respostaPiadaPorCategoria: Resource<DetalhesPiada>
    private val erro: Exception = mock()
    private lateinit var respostaErro: Resource<Any>

    @Before
    fun antesTeste() {
        responseHandler = ResponseHandler()
        apiChuckNorris = mock()
        repositorio = PiadasRepositoryImpl(apiChuckNorris, responseHandler)
        listaCategoriasApi = listOf(categoria_1, categoria_2)
        listaCategoriasObjeto = listOf(
            ChuckNorrisCategorias(categoria_1),
            ChuckNorrisCategorias(categoria_2)
        )
        detalhesPiada = DetalhesPiada(
            categories = listOf(categoria_1, categoria_2),
            created_at = data_criacao,
            icon_url = url_icone,
            id = id_piada,
            updated_at = data_atualizacoa,
            url = url_piada,
            value = texto_piada
        )
        respostaPiadaPorCategoria = responseHandler.handleSuccess(detalhesPiada)
        respostaCategoriasAposSerTratada = responseHandler.handleSuccess(listaCategoriasObjeto)
        respostaErro = responseHandler.handleException(erro)
    }

    @Test
    fun `ao chamar pegarCategoriasApiChuckNorris() caso SUCESSO deve retornar RECURSO convertido encapsulado`() =
        runBlocking {
            `when`(apiChuckNorris.getCategorias()).thenReturn(listaCategoriasApi)
            assertEquals(
                respostaCategoriasAposSerTratada,
                repositorio.pegarCategoriasApiChuckNorris()
            )
        }

    @Test
    fun `ao chamar pegarCategoriasApiChuckNorris() caso ERRO deve retornar ERRO encapsulado`() =
        runBlocking {
            given(apiChuckNorris.getCategorias()).willAnswer { throw erro }
            assertEquals(respostaErro, repositorio.pegarCategoriasApiChuckNorris())
        }

    @Test
    fun `ao chamar pegarPiadaPorCategoria() caso SUCESSO deve retornar RECURSO encapsulado`() =
        runBlocking {
            `when`(apiChuckNorris.getPiadaAleatoriaPorCategoria(categoria_1)).thenReturn(
                detalhesPiada
            )
            assertEquals(respostaPiadaPorCategoria, repositorio.pegarPiadaPorCategoria(categoria_1))
        }

    @Test
    fun `ao chamar pegarPiadaPorCategoria() caso ERRO deve retornar ERRO encapsulado`() =
        runBlocking {
            given(apiChuckNorris.getPiadaAleatoriaPorCategoria(categoria_1)).willAnswer { throw erro }
            assertEquals(respostaErro, repositorio.pegarPiadaPorCategoria(categoria_1))
        }
}