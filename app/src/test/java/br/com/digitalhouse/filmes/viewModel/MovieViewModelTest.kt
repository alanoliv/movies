package br.com.digitalhouse.filmes.viewModel

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.digitalhouse.filmes.model.Movie
import br.com.digitalhouse.filmes.model.MovieResult
import br.com.digitalhouse.filmes.repository.MovieRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class MovieViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieRepository: MovieRepository

    @InjectMocks
    var movieViewModel = MovieViewModel()

    private var singleMovie: Single<MovieResult>? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Before
    fun setUpRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor {it.run()})
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }

    @Test
    fun getAllMoviesSuccess() {
        val movie = Movie(title = "Joker", posterPath = "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg")
        val movies = arrayListOf(movie)

        val movieResult = MovieResult(movies)

        singleMovie = Single.just(movieResult)
        Mockito.`when`(movieRepository.getAllMovies(1)).thenReturn(singleMovie)
        movieViewModel.getAllMovies(1)

        Assert.assertEquals(1, movieViewModel.movies.value?.size)
        Assert.assertEquals(View.GONE, movieViewModel.showProgress.value)
        Assert.assertEquals(View.GONE, movieViewModel.showErrorMessage.value)
    }

    @Test
    fun getAllMoviesError() {
        singleMovie = Single.error(Throwable())

        Mockito.`when`(movieRepository.getAllMovies(1)).thenReturn(singleMovie)
        movieViewModel.getAllMovies(1)

        Assert.assertEquals(null, movieViewModel.movies.value)
        Assert.assertEquals(View.GONE, movieViewModel.showProgress.value)
        Assert.assertEquals(View.VISIBLE, movieViewModel.showErrorMessage.value)
    }
}