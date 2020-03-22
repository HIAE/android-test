package com.example.chuckeinstein.ui.piadas.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.chuckeinstein.data.repositorios.PiadasRepository
import com.example.chuckeinstein.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PiadasViewModel @Inject constructor(
    private val piadasRepository: PiadasRepository
) : ViewModel() {

    fun getCategorias() = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(Resource.loading(null))
        val categorias = piadasRepository.pegarCategoriasApiChuckNorris()
        emit(categorias)
    }

    fun getPiadaPorCategoria(categoria: String) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.loading(null))
            val piadas = piadasRepository.pegarPiadaPorCategoria(categoria)
            emit(piadas)
        }
}