package com.renanparis.chuckjokes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.renanparis.chuckjokes.R
import kotlinx.android.synthetic.main.item_list_categories.view.*

class CategoriesAdapter(
        private val context: Context,
        private val categories: MutableList<String> = mutableListOf(),
        var onItemClickListener: (category: String) -> Unit = {}
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewCreated = LayoutInflater.from(context).inflate(
                R.layout.item_list_categories, parent, false)
        return ViewHolder(viewCreated)
    }

    override fun getItemCount(): Int = categories.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    fun update(categories: List<String>) {
        this.categories.clear()
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var category: String
        private val textJoke by lazy { itemView.tv_list_category }

        init {
            itemView.setOnClickListener {
                if (::category.isInitialized) {
                    onItemClickListener(category)
                }
            }
        }

        fun bind(category: String) {
            this.category = category
            textJoke.text = category
        }

    }
}