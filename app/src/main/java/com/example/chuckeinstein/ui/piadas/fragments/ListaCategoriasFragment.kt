package com.example.chuckeinstein.ui.piadas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chuckeinstein.R
import com.example.chuckeinstein.data.remoto.models.ChuckNorrisCategorias
import com.example.chuckeinstein.data.remoto.models.MensagemErro
import com.example.chuckeinstein.di.ViewModelFactory
import com.example.chuckeinstein.ui.piadas.adapters.CategoriasAdapter
import com.example.chuckeinstein.ui.piadas.adapters.IClickCategoria
import com.example.chuckeinstein.ui.piadas.viewmodels.PiadasViewModel
import com.example.chuckeinstein.utils.Status
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.lista_categorias_fragment.*
import javax.inject.Inject

class ListaCategoriasFragment : Fragment(), IClickCategoria {

    lateinit var viewModel: PiadasViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.lista_categorias_fragment, container, false)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PiadasViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniciarViews()
        adicionarValores()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    private fun adicionarValores() {
        viewModel.getCategorias().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { categorias -> adicionarListaCategorias(categorias) }
                Status.ERROR -> it.message?.let { msgErro -> adicionarMensagemErro(msgErro) }
                Status.LOADING -> mostrarLoading()
            }
        })
    }

    private fun mostrarLoading() {
        (rv_categorias.adapter as CategoriasAdapter).mostrarLoading()
    }

    private fun adicionarListaCategorias(listaCategorias: List<ChuckNorrisCategorias>) {
        (rv_categorias.adapter as CategoriasAdapter).adicionarCategorias(listaCategorias)
    }

    private fun adicionarMensagemErro(msgErro: MensagemErro) {
        (rv_categorias.adapter as CategoriasAdapter).adicionarMensagemErro(msgErro)
    }

    private fun iniciarViews() {
        rv_categorias.apply {
            this.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
            this.adapter = CategoriasAdapter(this@ListaCategoriasFragment)
        }
        swipetorefresh.isEnabled = false
    }

    private fun mostrarPiada(piada: String?) {
        piada?.let {
            val acao = ListaCategoriasFragmentDirections
                .actionMainFragmentToPiadaFragment(it)
            findNavController().navigate(acao)
        }
    }

    override fun clickCategoria(categoria: String) {
        viewModel.getPiadaPorCategoria(categoria).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { detalhes ->
                        mostrarPiada(detalhes.value)
                    }
                }
                Status.ERROR -> mostrarAlerta(getString(R.string.ocorreu_um_erro))
                Status.LOADING -> swipetorefresh.isRefreshing = true
            }

        })
    }

    private fun mostrarAlerta(texto: String) {
        Toast.makeText(requireContext(), texto, Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()
        swipetorefresh.isRefreshing = false
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }
}