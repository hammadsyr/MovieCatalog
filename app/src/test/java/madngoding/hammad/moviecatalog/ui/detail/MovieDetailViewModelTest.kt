package madngoding.hammad.moviecatalog.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import madngoding.hammad.moviecatalog.data.DataRepository
import madngoding.hammad.moviecatalog.data.source.local.entity.MovieEntity
import madngoding.hammad.moviecatalog.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieDetailViewModel

    @MockK
    lateinit var dataRepository: DataRepository

    @MockK
    lateinit var movie: MovieEntity

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getMovieDetail() {
        val id = "8619"

        val fakeMovie = Resource.success(movie)
        coEvery { fakeMovie.data?.id } returns id
        val movieDetail = MutableLiveData<Resource<MovieEntity>>()
        movieDetail.value = fakeMovie

        viewModel = MovieDetailViewModel(dataRepository, id)

        coEvery { dataRepository.getMovieDetail(id) } returns movieDetail
        viewModel.getMovieDetail.observeForever { }

        assertNotNull(viewModel.getMovieDetail)
        assertEquals(viewModel.getMovieDetail.value, fakeMovie)
    }
}