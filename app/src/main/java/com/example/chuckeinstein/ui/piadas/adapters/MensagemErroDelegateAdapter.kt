package com.example.chuckeinstein.ui.piadas.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chuckeinstein.R
import com.example.chuckeinstein.data.remoto.models.MensagemErro
import com.example.chuckeinstein.utils.ViewType
import com.example.chuckeinstein.utils.ViewTypeDelegateAdapter
import com.example.chuckeinstein.utils.inflate
import kotlinx.android.synthetic.main.item_categorias_erro.view.*

class MensagemErroDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        MensagemErroViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as MensagemErroViewHolder
        holder.bind(item as MensagemErro)
    }

    class MensagemErroViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.item_categorias_erro)
    ) {

        fun bind(erro: MensagemErro) = with(itemView) {
            tv_mensagem_erro.text = erro.mensagem
        }
    }
}