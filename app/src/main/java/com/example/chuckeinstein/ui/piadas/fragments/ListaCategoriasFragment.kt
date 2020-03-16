package com.example.chuckeinstein.ui.piadas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chuckeinstein.R
import com.example.chuckeinstein.data.remoto.Categorias
import com.example.chuckeinstein.data.remoto.MensagemErro
import com.example.chuckeinstein.di.ViewModelFactory
import com.example.chuckeinstein.ui.piadas.adapters.CategoriasAdapter
import com.example.chuckeinstein.ui.piadas.viewmodels.PiadasViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.lista_categorias_fragment.*
import javax.inject.Inject

class ListaCategoriasFragment : Fragment() {

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

    private fun adicionarValores() {
    }

    private fun adicionarListaCategorias(listaCategorias: List<Categorias>) {
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
            this.adapter = CategoriasAdapter()
        }
    }

    private fun mostrarPiada(piada: String) {
        val acao =
            ListaCategoriasFragmentDirections.actionMainFragmentToPiadaFragment(
                piada
            )
        findNavController().navigate(acao)
    }
}