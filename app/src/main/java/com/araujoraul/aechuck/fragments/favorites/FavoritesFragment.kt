package com.araujoraul.aechuck.fragments.favorites

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.aechuck.MainApplication
import com.araujoraul.aechuck.R
import com.araujoraul.aechuck.db.entities.FavoritesEntity
import com.araujoraul.aechuck.fragments.BaseFragment
import com.araujoraul.aechuck.fragments.favorites.adapter.FavoritesAdapter
import java.util.ArrayList

class FavoritesFragment : BaseFragment() {

    private lateinit var viewModel: FavoritesViewModel
    private lateinit var recyclerView: RecyclerView
    private var favoriteList = MainApplication.favoriteList

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)

        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        recyclerView = root.findViewById(R.id.recyclerView_favorites)
        recyclerView.let {
            it.layoutManager = LinearLayoutManager(context)
            it.itemAnimator = DefaultItemAnimator()
            it.setHasFixedSize(true)
        }

        if (savedInstanceState != null){
            val getFavoriteList = savedInstanceState.getParcelableArrayList<FavoritesEntity>("list")
            if (getFavoriteList != null) {
                favoriteList.addAll(getFavoriteList)
            }

        }

        return root
    }

    override fun onResume() {
        super.onResume()

        with(viewModel){

            getAllFavorites().observe(viewLifecycleOwner, Observer { favorites ->

                if (favorites.isNotEmpty()){

                    if (favoriteList.size != favorites.size){

                        try {
                            favoriteList.clear()
                        } finally {

                            favoriteList.addAll(favorites)
                            recyclerView.adapter = FavoritesAdapter(favoriteList)
                        }

                    }

                }

            })

        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)


        outState.putParcelableArrayList("list", favoriteList)
    }

}


