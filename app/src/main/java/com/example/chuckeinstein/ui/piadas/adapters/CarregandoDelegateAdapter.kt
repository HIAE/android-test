package com.example.chuckeinstein.ui.piadas.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chuckeinstein.R
import com.example.chuckeinstein.utils.ViewType
import com.example.chuckeinstein.utils.ViewTypeDelegateAdapter
import com.example.chuckeinstein.utils.inflate

class CarregandoDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        CarregandoViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {}

    class CarregandoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.item_categorias_carregando)
    )
}