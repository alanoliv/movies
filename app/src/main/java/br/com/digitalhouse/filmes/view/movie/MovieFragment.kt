package br.com.digitalhouse.filmes.view.movie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.digitalhouse.filmes.R
import br.com.digitalhouse.filmes.databinding.MovieFragmentBinding
import br.com.digitalhouse.filmes.model.Movie
import br.com.digitalhouse.filmes.util.BindingAdapters
import br.com.digitalhouse.filmes.viewModel.MovieViewModel
import kotlinx.android.synthetic.main.movie_fragment.*

class MovieFragment : Fragment() {

    companion object {
        val TAG: String = MovieFragment::class.java.simpleName
        fun newInstance() = MovieFragment()
    }

    private lateinit var viewModel: MovieViewModel
    lateinit var binding: MovieFragmentBinding
    private lateinit var listener: OnMovieItemClick

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this)
            .get(MovieViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.movie_fragment, container, false)
        binding = MovieFragmentBinding.bind(rootView)
        binding.viewModel = viewModel

        observeVieModel()
        bindMovies()
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMovieItemClick) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnCountryItemClick.")
        }
    }

    private fun bindMovies() {
        binding.recyclerViewFilmes.adapter = MovieAdapter(arrayListOf()) { movie ->
            listener.onMovieSelected(movie)
        }
        binding.recyclerViewFilmes.layoutManager = LinearLayoutManager(context)
        viewModel.getAllMovies(1)
    }

    private fun observeVieModel() {
        viewModel.movies.observe(this, Observer { result ->
            binding.viewModel = viewModel
            result?.let {
                BindingAdapters.setItems(recyclerViewFilmes, it.toMutableList())
            }
        })
    }

    interface OnMovieItemClick {
        fun onMovieSelected(movie: Movie)
    }
}
