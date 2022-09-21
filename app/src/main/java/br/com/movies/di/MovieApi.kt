package br.com.movies.di

import br.com.movies.model.MovieResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing?api_key=0bdc2be3ccaae2cfd1d76e7660602738")
    fun getAllMovies(@Query("page") pagina: Int): Single<MovieResult>
}