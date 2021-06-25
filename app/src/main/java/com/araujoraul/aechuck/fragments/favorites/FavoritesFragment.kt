package com.araujoraul.aechuck.fragments.favorites

import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.aechuck.MainApplication
import com.araujoraul.aechuck.R
import com.araujoraul.aechuck.db.entities.FavoritesEntity
import com.araujoraul.aechuck.fragments.BaseFragment
import com.araujoraul.aechuck.fragments.favorites.adapter.FavoritesAdapter
import com.araujoraul.aechuck.fragments.favorites.helper.ButtonHelperClickListener
import com.araujoraul.aechuck.fragments.favorites.helper.MyButton
import com.araujoraul.aechuck.fragments.favorites.helper.SwipeHelper
import com.araujoraul.aechuck.utils.toast
import java.util.ArrayList

class FavoritesFragment : BaseFragment() {

    private lateinit var viewModel: FavoritesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var txtFavoriteNotFound: TextView
    private val adapter by lazy { FavoritesAdapter(emptyList()) }

    private var favoriteList = MainApplication.favoriteList

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)

        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        txtFavoriteNotFound = root.findViewById(R.id.txtFavoriteNotFound)
        recyclerView = root.findViewById(R.id.recyclerView_favorites)
        recyclerView.let {
            it.layoutManager = LinearLayoutManager(context)
            it.itemAnimator = DefaultItemAnimator()
            it.setHasFixedSize(true)
        }
        ItemTouchHelper(

            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){

                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    return false
                }
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val checkId = favoriteList[position].id

                    favoriteList.removeAt(position)
                    adapter.deleteFavoriteById(checkId)
                    adapter.notifyDataSetChanged()
                    recyclerView.adapter = FavoritesAdapter(favoriteList)
                }
            }

        ).attachToRecyclerView(recyclerView)




        if (savedInstanceState != null){
            val getFavoriteList = savedInstanceState.getParcelableArrayList<FavoritesEntity>("list")
            if (getFavoriteList != null) {
                favoriteList.addAll(getFavoriteList)
            }

        }

        setupFavoriteSwipe()

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
                } else txtFavoriteNotFound.visibility = View.VISIBLE

            })
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList("list", favoriteList)
    }

    private fun setupFavoriteSwipe(){


    }


}


