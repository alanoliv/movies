package br.com.movies.injection.component

import br.com.movies.injection.module.ApiModule
import br.com.movies.repository.MovieRepository
import br.com.movies.ui.movie.MovieViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(repository: MovieRepository)

    fun inject(viewModel: MovieViewModel)
}