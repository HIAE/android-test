package com.example.chuckeinstein.ui.piadas.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chuckeinstein.R
import com.example.chuckeinstein.data.remoto.models.ChuckNorrisCategorias
import com.example.chuckeinstein.utils.ViewType
import com.example.chuckeinstein.utils.ViewTypeDelegateAdapter
import com.example.chuckeinstein.utils.inflate
import kotlinx.android.synthetic.main.item_categorias.view.*

class CategoriasDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return CategoriasViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as CategoriasViewHolder
        holder.bind(item as ChuckNorrisCategorias)
    }

    class CategoriasViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.item_categorias)
    ) {
        fun bind(item: ChuckNorrisCategorias) = with(itemView) {
            tv_titulo_categoria.text = item.piada
        }
    }
}