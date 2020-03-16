package com.example.chuckeinstein.ui.piadas

import androidx.lifecycle.ViewModel
import com.example.chuckeinstein.data.repositorios.PiadasRepository
import javax.inject.Inject

class PiadasViewModel @Inject constructor(
    private val piadasRepository: PiadasRepository
) : ViewModel()