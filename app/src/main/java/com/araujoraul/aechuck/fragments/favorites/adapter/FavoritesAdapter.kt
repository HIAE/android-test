package com.araujoraul.aechuck.fragments.favorites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.aechuck.MainApplication
import com.araujoraul.aechuck.R
import com.araujoraul.aechuck.db.AppDatabase
import com.araujoraul.aechuck.db.entities.FavoritesEntity
import com.araujoraul.aechuck.utils.Coroutines
import kotlinx.android.synthetic.main.favorites_list.view.*

class FavoritesAdapter(
    val favorites: List<FavoritesEntity>
) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private var favorite = FavoritesEntity()
    private val app = MainApplication.getInstance()
    private val db = AppDatabase.getInstance(app.applicationContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoritesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.favorites_list, parent, false))

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {

         favorite = favorites[position]
        val view = holder.itemView

        with(view){
            joke.text = favorite.joke
            category.text = "Categoria: ${favorite.category}"
        }
    }
    fun deleteFavoriteById(checkId: Int) {
       Coroutines.io { db.favoritesDao().deleteFavoriteByIdFromDatabase(checkId) }
    }

    class FavoritesViewHolder(view: View) : RecyclerView.ViewHolder(view)
}