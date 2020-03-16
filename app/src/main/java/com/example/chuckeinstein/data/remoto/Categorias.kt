package com.example.chuckeinstein.data.remoto

import com.example.chuckeinstein.core.Constantes
import com.example.chuckeinstein.utils.ViewType

data class Categorias(val nome: String) : ViewType {
    override fun getViewType(): Int = Constantes.ADAPTERS.VIEW_TYPE_PIADAS_LISTA
}