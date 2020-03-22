package com.example.chuckeinstein.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chuckeinstein.data.remoto.models.ChuckNorrisCategorias

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun List<String>.converterParaListChuckCategorias(): List<ChuckNorrisCategorias> {
    val respostaConvertida: MutableList<ChuckNorrisCategorias> = mutableListOf()
    this.forEach { respostaConvertida.add(ChuckNorrisCategorias(it)) }
    return respostaConvertida
}