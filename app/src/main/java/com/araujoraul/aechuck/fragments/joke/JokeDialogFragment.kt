package com.araujoraul.aechuck.fragments.joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.araujoraul.aechuck.MainApplication
import com.araujoraul.aechuck.R
import com.araujoraul.aechuck.fragments.BaseDialogFragment
import com.araujoraul.aechuck.utils.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_dialog_joke.*
import kotlinx.coroutines.delay

class JokeDialogFragment : BaseDialogFragment() {

    private lateinit var viewModel: JokeViewModel
    private lateinit var imgCloseDialog: ImageButton
    private lateinit var titleCategory: TextView
    private lateinit var icon: ImageView
    private lateinit var joke: TextView
    private lateinit var btnRandomJoke: Button
    private lateinit var favorite: ImageButton
    private lateinit var progress: ProgressBar
    private lateinit var imgNoInternet: ImageView
    private lateinit var imgServerError: ImageView
    private var getTitleCategory = ""
    private var savedIcon = MainApplication.savedIcon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            val args = arguments
            getTitleCategory = args?.getString("category").toString().trim()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dialog_joke, container, false)

        viewModel = ViewModelProvider(this).get(JokeViewModel::class.java)
        titleCategory = root.findViewById(R.id.titleCategory)
        imgCloseDialog = root.findViewById(R.id.btnImageClose)
        btnRandomJoke = root.findViewById(R.id.btnJokeUpdate)
        progress = root.findViewById(R.id.progress_joke)
        imgNoInternet = root.findViewById(R.id.imgNoInternet_joke)
        imgServerError = root.findViewById(R.id.imgServerError_joke)
        favorite = root.findViewById(R.id.favorite)
        favorite.setBackgroundResource(R.drawable.ic_favorite_border)
        icon = root.findViewById(R.id.avatar)
        joke = root.findViewById(R.id.joke)

        imgCloseDialog.setOnClickListener { dismiss() }
        titleCategory.text = "Categoria: ${getTitleCategory}"


        if (savedInstanceState != null) {
            val getJoke = savedInstanceState.getString("joke")
            val getFavorite = savedInstanceState.getInt("favorite")
            val getIcon = savedInstanceState.getInt("icon")
            val getButton = savedInstanceState.getInt("button")
            val getSavedIcon = savedInstanceState.getString("savedIcon")

            joke.text = getJoke.toString()
            icon.visibility = getIcon
            favorite.visibility = getFavorite
            btnRandomJoke.visibility = getButton
            savedIcon = getSavedIcon

            Picasso.with(context).load(savedIcon).fit()
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.chuck_norris)
                .into(icon)

        }

        setupMessagesErrorAndProgressBar()
        return root
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launchWhenStarted {
            if (joke.text.toString().isEmpty())
                taskRandomJokeByCategory(getTitleCategory.trim())

            btnRandomJoke.setOnClickListener { v ->

                try {
                    v.setClickEnabled(false)
                    favorite.setClickEnabled(false)
                    taskRandomJokeByCategory(getTitleCategory.trim())
                } finally {
                   Coroutines.main {
                       delay(1_000) // 1 sec
                       v.setClickEnabled(true)
                       favorite.let {
                           it.setClickEnabled(true)
                           it.setBackgroundResource(R.drawable.ic_favorite_border)
                       }
                   }

                }
            }

            favorite.setOnClickListener { v ->

                try {
                    v.setClickEnabled(false)
                    favorite.setBackgroundResource(R.drawable.ic_favorited)
                    saveJokeToFavorites(joke.text.toString(), getTitleCategory)
                } finally {

                    with(viewModel) {
                        favoriteHasSaved.observe(viewLifecycleOwner, Observer {
                            it.getContentIfNotHandled().let {
                                if (it == true) v.snackbar("Adicionado aos favoritos com sucesso :)")
                            }
                        })

                        favoritedError.observe(viewLifecycleOwner, Observer {
                            it.getContentIfNotHandled().let {
                                if (it == true) {
                                    v.snackbar("Ocorreu um erro :(\n Por favor, tente novamente...")
                                    v.setClickEnabled(true)
                                }
                            }
                        })
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("joke", joke.text.toString().trim())
        outState.putInt("favorite", 0)
        outState.putInt("button", 0)
        outState.putInt("icon", 0)
        outState.putString("savedIcon", savedIcon)
    }

    private fun saveJokeToFavorites(joke: String, category: String) =
        viewModel.saveJokeToFavorites(joke, category)

    private fun taskRandomJokeByCategory(category: String) {

        with(viewModel) {

            taskRandomJokeByCategory(category)

            showRandomJoke.observe(viewLifecycleOwner, Observer { response ->

                if (response != null) {

                    if (joke.text.toString().isNullOrBlank() || !joke.text.toString().trim()
                            .equals(response.value.trim())
                    ) {

                        try {
                            joke.text = response.value

                            Picasso.with(context).load(response.icon).fit()
                                .centerCrop()
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.chuck_norris)
                                .into(icon)

                            savedIcon = response.icon

                        } finally {
                            icon.visibility = View.VISIBLE
                            btnRandomJoke.visibility = View.VISIBLE
                            favorite.visibility = View.VISIBLE
                        }

                    }

                }

            })
        }

    }

    private fun setupMessagesErrorAndProgressBar() {

        with(viewModel) {

            showProgressBar.observe(viewLifecycleOwner, Observer {
                if (it == true) progress.show()
                else progress.hide()
            })

            showMessageNoInternet.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled().let {
                    if (it == true) imgNoInternet.visibility = View.VISIBLE
                    else imgNoInternet.visibility = View.GONE
                }
            })

            showMessageServerError.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled().let {
                    if (it == true) imgServerError.visibility = View.VISIBLE
                    else imgServerError.visibility = View.GONE
                }
            })

        }

    }

}