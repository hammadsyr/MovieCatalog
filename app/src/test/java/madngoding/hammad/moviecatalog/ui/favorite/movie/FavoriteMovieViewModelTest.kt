package madngoding.hammad.moviecatalog.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import madngoding.hammad.moviecatalog.data.DataRepository
import madngoding.hammad.moviecatalog.data.source.local.entity.MovieEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteMovieViewModel

    @MockK
    lateinit var dataRepository: DataRepository

    @MockK
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getFavoriteMovieTest() {
        val fakeMovies = pagedList
        coEvery { pagedList.size } returns 5
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = fakeMovies

        viewModel = FavoriteMovieViewModel(dataRepository)

        coEvery { dataRepository.getFavoriteMovie() } returns movies
        viewModel.getFavoriteMovie().observeForever { }

        assertNotNull(viewModel.getFavoriteMovie())
        assertEquals(viewModel.getFavoriteMovie().value, fakeMovies)
    }
}