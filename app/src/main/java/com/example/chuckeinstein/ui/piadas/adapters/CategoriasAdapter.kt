package com.example.chuckeinstein.ui.piadas.adapters

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.chuckeinstein.core.Constantes
import com.example.chuckeinstein.core.Constantes.LISTAS.PRIMEIRA_POSICAO
import com.example.chuckeinstein.core.Constantes.LISTAS.UM_ITEM
import com.example.chuckeinstein.data.remoto.models.ChuckNorrisCategorias
import com.example.chuckeinstein.data.remoto.models.MensagemErro
import com.example.chuckeinstein.utils.ViewType
import com.example.chuckeinstein.utils.ViewTypeDelegateAdapter


class CategoriasAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itens: ArrayList<ViewType> = arrayListOf()
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    private val itemCarregar = object : ViewType {
        override fun getViewType(): Int = Constantes.ADAPTERS.VIEW_TYPE_PIADAS_CARREGAR
    }

    init {
        delegateAdapters.put(
            Constantes.ADAPTERS.VIEW_TYPE_PIADAS_CARREGAR,
            CarregandoDelegateAdapter()
        )
        delegateAdapters.put(
            Constantes.ADAPTERS.VIEW_TYPE_PIADAS_LISTA,
            CategoriasDelegateAdapter()
        )
        delegateAdapters.put(
            Constantes.ADAPTERS.VIEW_TYPE_PIADAS_ERRO,
            MensagemErroDelegateAdapter()
        )
        itens.add(itemCarregar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType)?.onCreateViewHolder(parent)
            ?: run {
                throw IllegalStateException("ViewType não reconhecido")
            }
    }

    override fun getItemCount(): Int = itens.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return delegateAdapters.get(getItemViewType(position))
            ?.onBindViewHolder(holder, this.itens[position])
            ?: run {
                throw IllegalStateException("ViewType não reconhecido")
            }
    }

    override fun getItemViewType(position: Int): Int {
        return this.itens[position].getViewType()
    }

    fun adicionarCategorias(categorias: List<ChuckNorrisCategorias>) {
        itens.removeAt(PRIMEIRA_POSICAO)
        notifyItemRemoved(PRIMEIRA_POSICAO)
        itens.addAll(categorias)
        notifyItemRangeChanged(PRIMEIRA_POSICAO, categorias.size)
    }

    fun adicionarMensagemErro(erro: MensagemErro) {
        itens.removeAt(PRIMEIRA_POSICAO)
        notifyItemRemoved(PRIMEIRA_POSICAO)
        itens.add(erro)
        notifyItemRangeChanged(PRIMEIRA_POSICAO, UM_ITEM)
    }
}