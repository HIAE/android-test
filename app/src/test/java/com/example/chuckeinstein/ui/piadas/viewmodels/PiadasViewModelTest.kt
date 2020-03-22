package com.example.chuckeinstein.ui.piadas.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.chuckeinstein.data.remoto.models.ChuckNorrisCategorias
import com.example.chuckeinstein.data.remoto.models.DetalhesPiada
import com.example.chuckeinstein.data.repositorios.PiadasRepository
import com.example.chuckeinstein.utils.Resource
import com.example.chuckeinstein.utils.ResponseHandler
import com.example.chuckeinstein.utils.Status
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.timeout
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class PiadasViewModelTest {
    private val mensagem_erro = "Não encontrado"
    private val data_criacao = "2020-01-05 13:42:18.823766"
    private val url_icone = "https://assets.chucknorris.host/img/avatar/chuck-norris.png"
    private val id_piada = "0wdewlp2tz-mt_upesvrjw"
    private val data_atualizacoa = "2020-01-05 13:42:18.823766"
    private val url_piada = "https://api.chucknorris.io/jokes/0wdewlp2tz-mt_upesvrjw"
    private val texto_piada =
        "Chuck Norris does not follow fashion trends, they follow him. But then he turns around and kicks their ass. Nobody follows Chuck Norris."
    private val categoria_1 = "categoria 1"
    private val categoria_2 = "categoria 2"
    private lateinit var viewModel: PiadasViewModel
    private lateinit var repositorio: PiadasRepository

    private lateinit var respostaCategoriaSucesso: Resource<List<ChuckNorrisCategorias>>
    private lateinit var respostaPiadaSucesso: Resource<DetalhesPiada>
    private lateinit var respostaErro: Resource<Exception>
    private lateinit var respostaCarregar: Resource<Any>

    private lateinit var listaCategorias: List<ChuckNorrisCategorias>
    private lateinit var detalhesPiada: DetalhesPiada

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var observadorPiadas: Observer<Resource<Any>>

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun antesTeste() {
        Dispatchers.setMain(mainThreadSurrogate)
        listaCategorias = listOf(
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

        respostaCategoriaSucesso = ResponseHandler().handleSuccess(listaCategorias)
        respostaPiadaSucesso = ResponseHandler().handleSuccess(detalhesPiada)
        respostaErro = ResponseHandler().handleException(Exception(mensagem_erro))
        respostaCarregar = Resource(Status.LOADING, null, null)
        repositorio = mock()
        observadorPiadas = mock()
        viewModel = PiadasViewModel(repositorio)
    }

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @After
    fun depoisTeste() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `quando chamar metodo getCategorias() caso SUCESSO, deve retornar CARREGAR depois RECURSO`() =
        runBlocking {
            `when`(repositorio.pegarCategoriasApiChuckNorris()).thenReturn(respostaCategoriaSucesso)
            viewModel.getCategorias().observeForever(observadorPiadas)
            viewModel.getCategorias()
            delay(10)
            verify(repositorio).pegarCategoriasApiChuckNorris()
            verify(observadorPiadas, timeout(50)).onChanged(respostaCarregar)
            verify(observadorPiadas, timeout(50)).onChanged(respostaCategoriaSucesso)
        }

    @Test
    fun `quando chamar metodo getCategorias() caso ERRO, deve retornar CARREGAR depois EXCECAO`() =
        runBlocking {
            `when`(repositorio.pegarCategoriasApiChuckNorris()).thenReturn(respostaErro)
            viewModel.getCategorias().observeForever(observadorPiadas)
            viewModel.getCategorias()
            delay(10)
            verify(repositorio).pegarCategoriasApiChuckNorris()
            verify(observadorPiadas, timeout(50)).onChanged(respostaCarregar)
            verify(observadorPiadas, timeout(50)).onChanged(respostaErro)
        }

    @Test
    fun `quando chamar metodo getPiadaPorCategoria(categoria) caso SUCESSO, deve retornar CARREGAR depois RECURSO`() =
        runBlocking {
            `when`(repositorio.pegarPiadaPorCategoria(categoria_1)).thenReturn(respostaPiadaSucesso)
            viewModel.getPiadaPorCategoria(categoria_1).observeForever(observadorPiadas)
            viewModel.getPiadaPorCategoria(categoria_1)
            delay(10)
            verify(repositorio).pegarPiadaPorCategoria(categoria_1)
            verify(observadorPiadas, timeout(50)).onChanged(respostaCarregar)
            verify(observadorPiadas, timeout(50)).onChanged(respostaPiadaSucesso)
        }

    @Test
    fun `quando chamar metodo getPiadaPorCategoria(categoria) caso ERRO, deve retornar CARREGAR depois ERRO`() =
        runBlocking {
            `when`(repositorio.pegarPiadaPorCategoria(categoria_1)).thenReturn(respostaErro)
            viewModel.getPiadaPorCategoria(categoria_1).observeForever(observadorPiadas)
            viewModel.getPiadaPorCategoria(categoria_1)
            delay(10)
            verify(repositorio).pegarPiadaPorCategoria(categoria_1)
            verify(observadorPiadas, timeout(50)).onChanged(respostaCarregar)
            verify(observadorPiadas, timeout(50)).onChanged(respostaErro)
        }
}