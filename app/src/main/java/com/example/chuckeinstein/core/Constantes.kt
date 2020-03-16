package com.example.chuckeinstein.core

object Constantes {
    object URL_API_CHUCKNORRIS {
        const val URL_BASE = "https://api.chucknorris.io/jokes/"
        const val MODIFICADOR_CATEGORIAS = "categories"
        const val MODIFICADOR_ALEATORIO_POR_CATEGORIA = "random?category="
        const val URL_MODIFICADOR_BUSCA_POR_TEXTO = "search?query="
    }

    object ADAPTERS {
        const val VIEW_TYPE_PIADAS_LISTA = 0
        const val VIEW_TYPE_PIADAS_CARREGAR = 1
        const val VIEW_TYPE_PIADAS_ERRO = 2
    }

    object LISTAS {
        const val PRIMEIRA_POSICAO = 0
        const val UM_ITEM = 1
    }
}