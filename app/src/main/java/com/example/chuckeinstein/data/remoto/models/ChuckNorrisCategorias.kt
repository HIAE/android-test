package com.example.chuckeinstein.data.remoto.models

import com.example.chuckeinstein.core.Constantes
import com.example.chuckeinstein.utils.ViewType

data class ChuckNorrisCategorias(val piada: String) : ViewType {
    override fun getViewType(): Int = Constantes.ADAPTERS.VIEW_TYPE_PIADAS_LISTA
}