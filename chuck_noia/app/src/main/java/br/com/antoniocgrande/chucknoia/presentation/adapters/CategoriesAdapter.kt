package br.com.antoniocgrande.chucknoia.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.antoniocgrande.chucknoia.R
import br.com.antoniocgrande.chucknoia.data.model.Category
import kotlinx.android.synthetic.main.item_categories.view.*

/* Copyright 2020.
 ************************************************************
 * Project     : ChuckNoia
 * Description : br.com.antoniocgrande.chucknoia.presentation.adapters
 * Created by  : antoniocgrande
 * Date        : 06/05/2020 21:43
 ************************************************************/
class CategoriesAdapter(
    private val categories: MutableList<Category> = mutableListOf(),
    private val onCategorySelected: (Category) -> Unit
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) =
        holder.bindView(categories[position], onCategorySelected)

    class CategoriesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(item: Category, onCategorySelected: (Category) -> Unit) {
            with(view) {
                textViewCategory.text = item.category
                itemView.setOnClickListener { onCategorySelected(item) }
            }
        }

    }

}