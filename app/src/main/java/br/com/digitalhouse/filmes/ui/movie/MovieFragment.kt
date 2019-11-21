package br.com.digitalhouse.filmes.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.digitalhouse.filmes.R
import br.com.digitalhouse.filmes.databinding.MovieFragmentBinding
import br.com.digitalhouse.filmes.util.BindingAdapters
import kotlinx.android.synthetic.main.movie_fragment.*

class MovieFragment : Fragment() {

    companion object {
        val TAG: String = MovieFragment::class.java.simpleName
        fun newInstance() = MovieFragment()
    }

    private lateinit var viewModel: MovieViewModel
    lateinit var binding: MovieFragmentBinding

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

    private fun bindMovies() {
        binding.recyclerViewFilmes.adapter =
            MovieAdapter(arrayListOf()) { movie ->
                val bundle = Bundle().apply {
                    this.putSerializable("movie", movie)
                }
                findNavController().navigate(R.id.action_movie_detail, bundle)
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
}
