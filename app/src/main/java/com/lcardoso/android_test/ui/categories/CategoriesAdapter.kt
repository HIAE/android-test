package com.lcardoso.android_test.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lcardoso.android_test.R
import kotlinx.android.synthetic.main.category_item.view.*

class CategoriesAdapter(
    private val categories: List<String>,
    private val onClickListener: (category: String) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesAdapter.CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: CategoriesAdapter.CategoryViewHolder, position: Int) {
        holder.bindView(categories[position])
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvCategory = view.tvCategory

        fun bindView(category: String) {
            tvCategory.text = category

            itemView.setOnClickListener { onClickListener.invoke(category) }
        }
    }
}