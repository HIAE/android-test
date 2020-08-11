package com.wesleyerick.chuckjokes.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wesleyerick.chuckjokes.R
import com.wesleyerick.chuckjokes.entity.Categories
import kotlinx.android.synthetic.main.recycler_category_item.view.*

class PhraseAdapter : RecyclerView.Adapter<PhraseAdapter.ViewHolder>(){

    var categories : ArrayList<String> = ArrayList()

    fun configData(categories: ArrayList<String>){
        this.categories = categories
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    lateinit var categoriesListener : CategoriesListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhraseAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.recycler_category_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    interface CategoriesListener {
        fun onItemClick(position: Int)
    }

    fun listener(listener : CategoriesListener){
        this.categoriesListener = listener
    }

    override fun onBindViewHolder(holder: PhraseAdapter.ViewHolder, position: Int) {

        val categories = categories[position]

        holder.itemView.textCategory.text = categories

        holder.itemView.btnItem.setOnClickListener {
            categoriesListener.onItemClick(position)
        }
    }

}