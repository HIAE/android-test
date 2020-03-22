package com.example.chuckeinstein.ui.piadas.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.chuckeinstein.data.remoto.models.ChuckNorrisCategorias
import com.example.chuckeinstein.data.repositorios.PiadasRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PiadasViewModel @Inject constructor(
    private val piadasRepository: PiadasRepository
) : ViewModel() {

    fun getCategorias() = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        val categorias = piadasRepository.pegarCategoriasApiChuckNorris()
        val listaNova: MutableList<ChuckNorrisCategorias> = mutableListOf()
        categorias.forEach { listaNova.add(ChuckNorrisCategorias(it)) }
        emit(listaNova)
    }
}