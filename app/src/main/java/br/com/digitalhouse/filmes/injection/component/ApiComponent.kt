package br.com.digitalhouse.filmes.injection.component

import br.com.digitalhouse.filmes.injection.module.ApiModule
import br.com.digitalhouse.filmes.repository.MovieRepository
import br.com.digitalhouse.filmes.ui.movie.MovieViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(repository: MovieRepository)

    fun inject(viewModel: MovieViewModel)
}