package com.renanparis.chuckjokes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.renanparis.chuckjokes.R
import com.renanparis.chuckjokes.data.model.Joke
import kotlinx.android.synthetic.main.item_list_favorites_jokes.view.*

class FavoritesJokesAdapter(private val context: Context,
                            private val jokes: MutableList<Joke> = mutableListOf(),
                            var onItemClickListener: (joke: Joke) -> Unit = {}
) : RecyclerView.Adapter<FavoritesJokesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewCreated = LayoutInflater.from(context).inflate(R.layout.activity_favorites_jokes
                , parent, false)
        return ViewHolder(viewCreated)
    }

    override fun getItemCount(): Int = jokes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(jokes[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var joke: Joke
        private val textJoke by lazy { itemView.tv_list_favorites_joke }

        init {
            itemView.setOnClickListener {
                if (::joke.isInitialized) {
                    onItemClickListener(joke)
                }
            }
        }

        fun bind(joke: Joke) {
            this.joke = joke
            textJoke.text = joke.value
        }

    }
}