package br.com.movies.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.movies.HomeActivity
import br.com.movies.R
import br.com.movies.databinding.FragmentDetailGalleryBinding
import br.com.movies.model.Movie

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

            val homeActivity = activity as HomeActivity
            movie.apply {
                homeActivity.titleToolbar(title)
            }

            dataBinding.movie = movie
        }
    }
}