package com.wesleyerick.chuckjokes.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wesleyerick.chuckjokes.R
import com.wesleyerick.chuckjokes.presenter.MainPresenter
import com.wesleyerick.chuckjokes.router.startPhraseScreen
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay


//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //    @Inject

    var presenter: MainPresenter = MainPresenter()
    private val categoriesAdapter = PhraseAdapter()
    private var array = ArrayList<String>()


    companion object {
        private var phrase = ""
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRandom.setOnClickListener {
            presenter.randomPress(this, this)
            GlobalScope.async {
                delay(2000)
                nextScreen()
            }
        }

        array = intent.getStringArrayListExtra("CATEGORIES")
        categoriesAdapter.configData(array)
        configClick()

        configRecyclerView( this)
    }

    override fun onBackPressed() {
    }

    fun configRecyclerView (activity : Activity) {
        val layoutInflater = LinearLayoutManager(activity)
        recycler.layoutManager = layoutInflater
        recycler.adapter = categoriesAdapter
    }

    fun configClick(){
            val callback = object : PhraseAdapter.CategoriesListener {
                override fun onItemClick(position: Int) {
                    array[position]
                    presenter.onCategoryClick(array[position])

                    btnRandom.visibility = View.INVISIBLE
                    progressBar.visibility = View.VISIBLE

                    GlobalScope.async {
                        delay(2000)
                        nextScreen()
                    }


                }
            }

            categoriesAdapter.listener(callback)
    }

    override fun onRestart() {
        super.onRestart()
        btnRandom.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    fun getMessage(it: String) {
            phrase = it
    }

    fun nextScreen(){
        startPhraseScreen(this, this, phrase)
    }

}

