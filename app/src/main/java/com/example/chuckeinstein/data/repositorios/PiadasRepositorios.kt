package com.example.chuckeinstein.data.repositorios

import com.example.chuckeinstein.data.remoto.conexaoapi.ApiChuckNorris
import javax.inject.Inject

class PiadasRepository @Inject constructor(
        private val apiChuckNorris: ApiChuckNorris
) {
    suspend fun pegarCategoriasApiChuckNorris() = apiChuckNorris.getCategorias()
}