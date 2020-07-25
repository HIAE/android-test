package com.lcardoso.android_test.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lcardoso.android_test.R
import com.lcardoso.android_test.data.database.JokeEntity
import kotlinx.android.synthetic.main.joke_item.view.*

class FavoritesAdapter(
    private val jokes: List<JokeEntity>,
    private val onClickListener: (joke: JokeEntity) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesAdapter.CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.joke_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount() = jokes.size

    override fun onBindViewHolder(holder: FavoritesAdapter.CategoryViewHolder, position: Int) {
        holder.bindView(jokes[position])
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvJoke = view.tvJoke
        private val tvJokeCategory = view.tvJokeCategory
        private val btDelete = view.ivDelete

        fun bindView(joke: JokeEntity) {
            tvJoke.text = joke.joke
            tvJokeCategory.text = joke.category
            btDelete.setOnClickListener { onClickListener.invoke(joke) }
        }
    }
}