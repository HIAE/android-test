package com.araujoraul.aechuck.fragments.piada

import android.icu.number.NumberFormatter.with
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.araujoraul.aechuck.R
import com.araujoraul.aechuck.fragments.BaseDialogFragment
import com.araujoraul.aechuck.utils.hide
import com.araujoraul.aechuck.utils.show
import com.araujoraul.aechuck.utils.toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_categorias.*
import kotlinx.android.synthetic.main.fragment_dialog_piada.*

class PiadaDialogFragment : BaseDialogFragment(){

    private lateinit var viewModel: PiadaViewModel
    private lateinit var imgCloseDialog: ImageButton
    private lateinit var titleCategory: TextView
    private lateinit var icon: ImageView
    private lateinit var joke: TextView
    private var getTitleCategory = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null){
            val args = arguments
            getTitleCategory = args?.getString("category").toString().trim()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_dialog_piada, container, false)

        viewModel = ViewModelProvider(this).get(PiadaViewModel::class.java)
        titleCategory = root.findViewById(R.id.titleCategory)
        imgCloseDialog = root.findViewById(R.id.btnImageClose)
        icon = root.findViewById(R.id.avatar)
        joke = root.findViewById(R.id.joke)

        imgCloseDialog.setOnClickListener { dismiss() }
        titleCategory.text = "Categoria: ${getTitleCategory}"

        setupMessagesErrorAndProgressBar()
        return root
    }

    override fun onResume() {
        super.onResume()
        taskRandomJokeByCategory(getTitleCategory.trim())
    }

    private fun taskRandomJokeByCategory(category: String) {

        with(viewModel){

            taskRandomJokeByCategory(category)

            showRandomJoke.observe(viewLifecycleOwner, Observer { response ->

                if (response != null) {

                    if (joke.text.toString().isNullOrBlank()){

                        joke.text = response.value

                        Picasso.with(context).load(response.icon).fit()
                            .centerCrop()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.chuck_norris)
                            .into(icon)

                    }

                }

            })

        }

    }

    private fun setupMessagesErrorAndProgressBar(){

        with(viewModel){

            showProgressBar.observe(viewLifecycleOwner, Observer {
                if (it == true) progress_joke.show()
                else progress_joke.hide()
            })

            showMessageNoInternet.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled().let {
                    if (it == true) context?.toast("Sem conexão com a internet :(\nTente novamente daqui a pouco...")
                }
            })

            showMessageServerError.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled().let {
                    if (it == true) context?.toast("Erro no servidor interno :( Tente novamente daqui a pouco...")
                }
            })

        }

    }

}