package madngoding.hammad.moviecatalog.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
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
class MovieViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel

    @MockK
    lateinit var dataRepository: DataRepository

    @MockK
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getMovieTest() {
        val fakeMovies = Resource.success(pagedList)
        coEvery { fakeMovies.data?.size } returns 20
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = fakeMovies

        viewModel = MovieViewModel(dataRepository)

        coEvery { dataRepository.getMovie() } returns movies
        viewModel.getMovie().observeForever { }

        assertNotNull(viewModel.getMovie())
        assertEquals(viewModel.getMovie().value, fakeMovies)
    }
}
