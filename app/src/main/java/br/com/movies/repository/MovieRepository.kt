package br.com.movies.repository


import br.com.movies.di.MovieApi
import br.com.movies.injection.component.DaggerApiComponent
import br.com.movies.model.MovieResult
import io.reactivex.Single
import javax.inject.Inject

class MovieRepository {

    @Inject
    lateinit var api : MovieApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getAllMovies(pages: Int) :Single<MovieResult> {
        return api.getAllMovies(pages)
    }
}