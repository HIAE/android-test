package com.example.chuckeinstein.data.repositorios

import com.example.chuckeinstein.data.remoto.conexaoapi.ApiChuckNorris
import com.example.chuckeinstein.data.remoto.models.ChuckNorrisCategorias
import com.example.chuckeinstein.data.remoto.models.DetalhesPiada
import com.example.chuckeinstein.utils.Resource
import com.example.chuckeinstein.utils.ResponseHandler
import com.example.chuckeinstein.utils.converterParaListChuckCategorias

class PiadasRepositoryImpl(
    private val apiChuckNorris: ApiChuckNorris,
    private val responseHandler: ResponseHandler
) : PiadasRepository {

    override suspend fun pegarCategoriasApiChuckNorris(): Resource<List<ChuckNorrisCategorias>> {
        return try {
            val resposta = apiChuckNorris.getCategorias()
            responseHandler.handleSuccess(resposta.converterParaListChuckCategorias())
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