package com.araujoraul.aechuck.fragments.categories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.aechuck.R
import kotlinx.android.synthetic.main.categories_list.view.*

class CategoriesAdapter(
    val categorias: List<String>,
    val onClick: (String) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.CategoriasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoriasViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.categories_list, parent, false))

    override fun getItemCount(): Int = categorias.size

    override fun onBindViewHolder(holder: CategoriasViewHolder, position: Int) {

        val categoria = categorias[position]
        val view = holder.itemView

        with(view){
            categoryName.text = categoria
            setOnClickListener { onClick(categoria) }
        }
    }

    class CategoriasViewHolder(view: View) : RecyclerView.ViewHolder(view)
}