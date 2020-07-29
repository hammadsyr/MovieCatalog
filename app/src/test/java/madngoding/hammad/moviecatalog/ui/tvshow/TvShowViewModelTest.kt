package madngoding.hammad.moviecatalog.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import madngoding.hammad.moviecatalog.data.DataRepository
import madngoding.hammad.moviecatalog.data.source.local.entity.TvShowEntity
import madngoding.hammad.moviecatalog.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TvShowViewModel

    @MockK
    lateinit var dataRepository: DataRepository

    @MockK
    lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getTvShowTest() {
        val fakeTvShow = Resource.success(pagedList)
        coEvery { fakeTvShow.data?.size } returns 20
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = fakeTvShow

        viewModel = TvShowViewModel(dataRepository)

        coEvery { dataRepository.getTvShow() } returns tvShows
        viewModel.getTvShow().observeForever { }

        assertNotNull(viewModel.getTvShow())
        assertEquals(viewModel.getTvShow().value, fakeTvShow)
    }
}