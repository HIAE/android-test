package com.renanparis.chuckjokes.ui.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.renanparis.chuckjokes.R
import com.renanparis.chuckjokes.ui.viewmodel.RandomJokeViewModel
import kotlinx.android.synthetic.main.activity_random_joke.*
import org.koin.android.viewmodel.ext.android.viewModel

class RandomJokeActivity : AppCompatActivity() {

    private lateinit var textJoke: TextView
    private val viewModel: RandomJokeViewModel by viewModel()
    private val category: String by lazy {
        "animal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_joke)
        initViews()
        searchRandomJoke()
    }

    private fun searchRandomJoke() {
        viewModel.getRandomJoke(category)

    }

    private fun initViews() {
        textJoke = tv_random_joke
    }
}
