package br.com.movies.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import br.com.movies.R
import br.com.movies.databinding.FragmentGalleryBinding
import br.com.movies.ui.movie.MovieViewModel
import br.com.movies.util.BindingAdapters

class GalleryFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    lateinit var binding: FragmentGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this)
            .get(MovieViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_gallery, container, false)
        binding = FragmentGalleryBinding.bind(rootView)
        binding.viewModel = viewModel

        observeVieModel()
        bindMovies()
        return binding.root
    }

    private fun bindMovies() {
        binding.recyclerviewgallery.adapter = GalleryAdapter(arrayListOf()) { movie ->
            val bundle = Bundle().apply {
                this.putSerializable("movie", movie)
            }
            findNavController().navigate(R.id.action_gallery_detail, bundle)
        }
        binding.recyclerviewgallery.layoutManager = GridLayoutManager(context, 2)
        viewModel.getAllMovies(1)
    }

    private fun observeVieModel() {
        viewModel.movies.observe(this, Observer { result ->
            binding.viewModel = viewModel
            result?.let {
                BindingAdapters.setItems(binding.recyclerviewgallery, it.toMutableList())
            }
        })
    }

}