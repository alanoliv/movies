package br.com.digitalhouse.filmes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.filmes.model.Movie
import br.com.digitalhouse.filmes.view.movie.MovieDetailFragment
import br.com.digitalhouse.filmes.view.movie.MovieFragment

class MainActivity : AppCompatActivity(), MovieFragment.OnMovieItemClick {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var countryFragment: MovieFragment? = supportFragmentManager.findFragmentByTag(MovieFragment.TAG) as? MovieFragment
        if (countryFragment == null) {
            countryFragment = MovieFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, countryFragment, MovieFragment.TAG)
                .commit()
        }
    }

    override fun onMovieSelected(movie: Movie) {
        var countryDetailFragment: MovieDetailFragment? = supportFragmentManager.findFragmentByTag(MovieDetailFragment.TAG) as? MovieDetailFragment
        if (countryDetailFragment == null) {
            countryDetailFragment = MovieDetailFragment.newInstance(movie)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, countryDetailFragment, MovieDetailFragment.TAG)
                .addToBackStack(MovieDetailFragment.TAG)
                .commit()
        }
    }
}
