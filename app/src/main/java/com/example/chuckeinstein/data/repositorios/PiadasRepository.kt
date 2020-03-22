package com.example.chuckeinstein.data.repositorios

import com.example.chuckeinstein.utils.Resource

interface PiadasRepository {
    suspend fun pegarCategoriasApiChuckNorris(): Resource<Any>
    suspend fun pegarPiadaPorCategoria(categoria: String): Resource<Any>
}