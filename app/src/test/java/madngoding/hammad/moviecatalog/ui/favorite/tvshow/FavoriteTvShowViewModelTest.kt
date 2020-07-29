package madngoding.hammad.moviecatalog.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import madngoding.hammad.moviecatalog.data.DataRepository
import madngoding.hammad.moviecatalog.data.source.local.entity.TvShowEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteTvShowViewModel

    @MockK
    lateinit var dataRepository: DataRepository

    @MockK
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getFavoriteTvShowTest() {
        val fakeTvShows = pagedList
        coEvery { pagedList.size } returns 5
        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = fakeTvShows

        viewModel = FavoriteTvShowViewModel(dataRepository)

        coEvery { dataRepository.getFavoriteTvShow() } returns tvShows
        viewModel.getFavoriteTvShow().observeForever { }

        assertNotNull(viewModel.getFavoriteTvShow())
        assertEquals(viewModel.getFavoriteTvShow().value, fakeTvShows)
    }
}