package br.com.digitalhouse.filmes.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.digitalhouse.filmes.R
import br.com.digitalhouse.filmes.databinding.FragmentDetailGalleryBinding
import br.com.digitalhouse.filmes.databinding.FragmentGalleryBinding
import br.com.digitalhouse.filmes.model.Movie
import br.com.digitalhouse.filmes.ui.movie.MovieAdapter
import br.com.digitalhouse.filmes.ui.movie.MovieViewModel
import br.com.digitalhouse.filmes.util.BindingAdapters

class GalleryDetailFragment : Fragment() {

    private lateinit var dataBinding: FragmentDetailGalleryBinding
    lateinit var movie : Movie

    companion object {
        val TAG: String = GalleryDetailFragment::class.java.simpleName
        private val MOVIE = "movie"

        fun newInstance(movie: Movie): GalleryDetailFragment {
            val args = Bundle()
            args.putSerializable(MOVIE, movie)
            val fragment = GalleryDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_detail_gallery, container, false)
        dataBinding = FragmentDetailGalleryBinding.bind(rootView)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            this.movie = it.getSerializable(MOVIE) as Movie
            dataBinding.movie = movie
        }
    }
}