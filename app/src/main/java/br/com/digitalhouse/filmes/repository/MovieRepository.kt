package br.com.digitalhouse.filmes.repository


import br.com.digitalhouse.filmes.di.MovieApi
import br.com.digitalhouse.filmes.injection.component.DaggerApiComponent
import br.com.digitalhouse.filmes.model.MovieResult
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