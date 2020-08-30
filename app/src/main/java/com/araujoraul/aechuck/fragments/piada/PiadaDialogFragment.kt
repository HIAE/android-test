package com.araujoraul.aechuck.fragments.piada

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.araujoraul.aechuck.R
import com.araujoraul.aechuck.fragments.BaseDialogFragment

class PiadaDialogFragment : BaseDialogFragment(){

    private lateinit var viewModel: PiadaViewModel
    private lateinit var imgCloseDialog: ImageButton
    private lateinit var titleCategory: TextView
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

        imgCloseDialog.setOnClickListener { dismiss() }
        titleCategory.text = "Categoria: ${getTitleCategory}"
        return root
    }

}