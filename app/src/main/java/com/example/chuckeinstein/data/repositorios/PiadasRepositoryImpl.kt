package com.example.chuckeinstein.data.repositorios

import com.example.chuckeinstein.data.remoto.conexaoapi.ApiChuckNorris
import com.example.chuckeinstein.data.remoto.models.ChuckNorrisCategorias
import com.example.chuckeinstein.data.remoto.models.DetalhesPiada
import com.example.chuckeinstein.utils.Resource
import com.example.chuckeinstein.utils.ResponseHandler

class PiadasRepositoryImpl(
    private val apiChuckNorris: ApiChuckNorris,
    private val responseHandler: ResponseHandler
) : PiadasRepository {

    override suspend fun pegarCategoriasApiChuckNorris(): Resource<List<ChuckNorrisCategorias>> {
        return try {
            val resposta = apiChuckNorris.getCategorias()
            val respostaConvertida: MutableList<ChuckNorrisCategorias> = mutableListOf()
            resposta.forEach { respostaConvertida.add(ChuckNorrisCategorias(it)) }
            responseHandler.handleSuccess(respostaConvertida)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun pegarPiadaPorCategoria(categoria: String): Resource<DetalhesPiada> {
        return try {
            val piada = apiChuckNorris.getPiadaAleatoriaPorCategoria(categoria)
            responseHandler.handleSuccess(piada)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}