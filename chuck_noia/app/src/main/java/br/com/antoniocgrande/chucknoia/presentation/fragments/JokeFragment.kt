package br.com.antoniocgrande.chucknoia.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.antoniocgrande.chucknoia.R
import br.com.antoniocgrande.chucknoia.data.model.Category
import br.com.antoniocgrande.chucknoia.data.model.Joke
import br.com.antoniocgrande.chucknoia.presentation.activities.HomeActivity.Companion.viewModel
import br.com.antoniocgrande.chucknoia.presentation.activities.HomeState
import kotlinx.android.synthetic.main.empty_state.*
import kotlinx.android.synthetic.main.fragment_joke.*


class JokeFragment : Fragment() {


    /**
     *
     * ATRIBUTES
     *
     */
    private var arg: Category? = null


    /**
     *
     * OVERRIDE METHODS
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_joke, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAttributes()
        setupState()
        setupJoke()
        setupListeners()
        setupToolbar()
    }


    /**
     *
     * SETUP METHODS
     *
     */
    private fun setupAttributes() {
        arg = arguments?.run {
            getString("CATEGORY") as Category
        }
    }

    private fun setupState() {
        viewModel.getState().observe(this, Observer { state ->
            when (state) {
                is HomeState.RandomJokeSuccess -> randomJokeSuccess(state.joke)
                is HomeState.RandomJokeFail -> randomJokeFail()
                is HomeState.HideEmptyState -> hideEmptyState()
            }
        })
    }

    private fun setupJoke() {
        arg?.let { viewModel.getRandomJoke(it) }
    }

    private fun setupListeners() {
        textViewTryAgain.setOnClickListener { arg?.let { viewModel.getRandomJoke(it) } }
        textViewRefreshJoke.setOnClickListener { textViewTryAgain.performClick() }
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity?)?.supportActionBar?.subtitle = "#$arg"
    }


    /**
     *
     * STATE METHODS
     *
     */
    private fun randomJokeSuccess(randomJoke: Joke) {
        textViewRandomJoke.text = randomJoke.value
    }

    private fun randomJokeFail() {
        showEmptyState()
    }

    private fun showEmptyState() {
        constraintLayoutEmptyState.visibility = View.VISIBLE
    }

    private fun hideEmptyState() {
        constraintLayoutEmptyState.visibility = View.GONE
    }

}
