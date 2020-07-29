package madngoding.hammad.moviecatalog.ui.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import madngoding.hammad.moviecatalog.data.DataRepository
import madngoding.hammad.moviecatalog.data.source.local.LocalDataSource
import madngoding.hammad.moviecatalog.data.source.local.entity.MovieEntity
import madngoding.hammad.moviecatalog.data.source.local.entity.TvShowEntity
import madngoding.hammad.moviecatalog.data.source.remote.RemoteDataSource
import madngoding.hammad.moviecatalog.data.source.remote.response.MovieResult
import madngoding.hammad.moviecatalog.data.source.remote.response.TvShowResult
import madngoding.hammad.moviecatalog.ui.utl.BaseTest
import madngoding.hammad.moviecatalog.ui.utl.LiveDataTestUtil
import madngoding.hammad.moviecatalog.ui.utl.PagedListUtil
import madngoding.hammad.moviecatalog.util.AppExecutors
import madngoding.hammad.moviecatalog.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class DataRepositoryTest {

    private val remote = mock(RemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private lateinit var dataRepository: DataRepository
    private lateinit var movieList: List<MovieResult>
    private lateinit var tvShowList: List<TvShowResult>

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun start() {
        dataRepository = FakeDataRepository(remote, localDataSource, appExecutors)
        movieList = BaseTest.getLocalMovie()
        tvShowList = BaseTest.getLocalTvShow()
    }

    @Test
    fun getMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(localDataSource.getMovie()).thenReturn(dataSourceFactory)
        dataRepository.getMovie()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(movieList))

        verify(localDataSource).getMovie()
        assertNotNull(movieEntities.data)
        assertEquals(movieList.size, movieEntities.data?.size)
    }

    @Test
    fun getTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(localDataSource.getTvShow()).thenReturn(dataSourceFactory)
        dataRepository.getTvShow()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(tvShowList))

        verify(localDataSource).getTvShow()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowList.size, tvShowEntities.data?.size)
    }

    @Test
    fun getMovieDetail() {
        val movie = Gson().toJsonTree(movieList[0]).asJsonObject
        val mv = Gson().fromJson<MovieResult>(movie, MovieResult::class.java)
        val entity = MovieEntity(mv.id.toString(), mv.title, mv.posterPath, mv.releaseDate, mv.overview, false)
        val id = mv.id.toString()

        val fakeMovieDetail = MutableLiveData<MovieEntity>()
        fakeMovieDetail.value = entity
        Mockito.`when`<LiveData<MovieEntity>>(localDataSource.getMovieWithId(id)).thenReturn(fakeMovieDetail)

        val movieEntity = LiveDataTestUtil.getValue(dataRepository.getMovieDetail(id))
        verify(localDataSource).getMovieWithId(id)

        assertNotNull(movieEntity.data)
        assertEquals(entity, movieEntity.data)


    }

    @Test
    fun getTvShowDetail() {
        val tvShow = Gson().toJsonTree(tvShowList[0]).asJsonObject
        val tv = Gson().fromJson<TvShowResult>(tvShow, TvShowResult::class.java)
        val entity = TvShowEntity(tv.id.toString(), tv.name, tv.posterPath, tv.firstAirDate, tv.overview, false)
        val id = tv.id.toString()

        val fakeTvShowDetail = MutableLiveData<TvShowEntity>()
        fakeTvShowDetail.value = entity
        Mockito.`when`<LiveData<TvShowEntity>>(localDataSource.getTvShowWithId(id)).thenReturn(fakeTvShowDetail)

        val tvShowEntity = LiveDataTestUtil.getValue(dataRepository.getTvShowDetail(id))
        verify(localDataSource).getTvShowWithId(id)

        assertNotNull(tvShowEntity.data)
        assertEquals(entity, tvShowEntity.data)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(localDataSource.getFavoriteMovie()).thenReturn(dataSourceFactory)
        dataRepository.getFavoriteMovie()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(movieList))

        verify(localDataSource).getFavoriteMovie()
        assertNotNull(movieEntities.data)
        assertEquals(movieList.size, movieEntities.data?.size)
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(localDataSource.getFavoriteTvShow()).thenReturn(dataSourceFactory)
        dataRepository.getFavoriteTvShow()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(tvShowList))

        verify(localDataSource).getFavoriteTvShow()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowList.size, tvShowEntities.data?.size)
    }
}

