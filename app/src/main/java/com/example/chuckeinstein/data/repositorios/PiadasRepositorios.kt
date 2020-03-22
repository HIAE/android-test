package com.example.chuckeinstein.data.repositorios

import com.example.chuckeinstein.data.remoto.models.ChuckNorrisCategorias
import com.example.chuckeinstein.data.remoto.models.DetalhesPiada
import com.example.chuckeinstein.utils.Resource

interface PiadasRepository {
    suspend fun pegarCategoriasApiChuckNorris(): Resource<List<ChuckNorrisCategorias>>
    suspend fun pegarPiadaPorCategoria(categoria: String): Resource<DetalhesPiada>
}