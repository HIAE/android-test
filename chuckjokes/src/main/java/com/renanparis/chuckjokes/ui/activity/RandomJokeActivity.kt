package com.renanparis.chuckjokes.ui.activity

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.renanparis.chuckjokes.R
import com.renanparis.chuckjokes.ui.viewmodel.RandomJokeViewModel
import com.renanparis.chuckjokes.utils.Status
import kotlinx.android.synthetic.main.activity_random_joke.*
import org.koin.android.viewmodel.ext.android.viewModel

class RandomJokeActivity : AppCompatActivity() {

    private lateinit var textJoke: TextView
    private val viewModel: RandomJokeViewModel by viewModel()
    private val category: String by lazy {
        intent.getStringExtra("category")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_joke)
        initViews()
        searchRandomJoke()
    }

    private fun searchRandomJoke() {
        viewModel.getRandomJoke(category).observe(this, Observer {
            it?.let {resource ->
                when(resource.status) {

                    Status.LOADING -> {

                    }
                    Status.ERROR -> {
                        Toast.makeText(this, resource.message, Toast.LENGTH_LONG).show()
                    }
                    Status.SUCCESS -> {
                        val joke = resource.data
                        textJoke.text = joke?.value
                    }
                }

            }
        })
    }

    private fun initViews() {
        textJoke = tv_random_joke
    }
}
