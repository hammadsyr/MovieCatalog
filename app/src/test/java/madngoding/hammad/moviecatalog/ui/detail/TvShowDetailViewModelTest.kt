package madngoding.hammad.moviecatalog.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
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
class TvShowDetailViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TvShowDetailViewModel

    @MockK
    lateinit var dataRepository: DataRepository

    @MockK
    lateinit var tvShow: TvShowEntity

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getTvShowDetail() {
        val id = "2734"

        val fakeTvShow = Resource.success(tvShow)
        coEvery { fakeTvShow.data?.id } returns id
        val tvShowDetail = MutableLiveData<Resource<TvShowEntity>>()
        tvShowDetail.value = fakeTvShow

        viewModel = TvShowDetailViewModel(dataRepository, id)

        coEvery { dataRepository.getTvShowDetail(id) } returns tvShowDetail
        viewModel.getTvShowDetail.observeForever { }

        assertNotNull(viewModel.getTvShowDetail)
        assertEquals(viewModel.getTvShowDetail.value, fakeTvShow)
    }
}