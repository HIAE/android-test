package com.araujoraul.aechuck.fragments.favorites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.aechuck.R
import com.araujoraul.aechuck.db.entities.FavoritesEntity
import kotlinx.android.synthetic.main.favorites_list.view.*

class FavoritesAdapter(
    val favorites: List<FavoritesEntity>
) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoritesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.favorites_list, parent, false))

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {

        val favorite = favorites[position]
        val view = holder.itemView

        with(view){
            joke.text = favorite.joke
            category.text = "Categoria: ${favorite.category}"
        }
    }

    class FavoritesViewHolder(view: View) : RecyclerView.ViewHolder(view)
}