package com.araujoraul.aechuck.fragments.categorias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.araujoraul.aechuck.R
import com.araujoraul.aechuck.fragments.BaseFragment

class CategoriasFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_categorias, container, false)

        return root
    }

}