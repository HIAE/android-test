package com.example.chuckeinstein.data.remoto.conexaoapi

import com.example.chuckeinstein.core.Constantes
import com.example.chuckeinstein.data.remoto.models.ChuckNorrisCategorias
import retrofit2.http.GET

interface ApiChuckNorris {
    @GET(Constantes.URL_API_CHUCKNORRIS.MODIFICADOR_CATEGORIAS)
    suspend fun getCategorias(): List<String>

    @GET(Constantes.URL_API_CHUCKNORRIS.MODIFICADOR_ALEATORIO_POR_CATEGORIA)
    suspend fun getPiadaAleatoriaPorCategoria(): ChuckNorrisCategorias
}