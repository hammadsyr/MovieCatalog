package madngoding.hammad.moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import madngoding.hammad.moviecatalog.data.source.local.LocalDataSource
import madngoding.hammad.moviecatalog.data.source.local.entity.MovieEntity
import madngoding.hammad.moviecatalog.data.source.local.entity.TvShowEntity
import madngoding.hammad.moviecatalog.data.source.remote.ApiResponse
import madngoding.hammad.moviecatalog.data.source.remote.RemoteDataSource
import madngoding.hammad.moviecatalog.data.source.remote.response.MovieResult
import madngoding.hammad.moviecatalog.data.source.remote.response.TvShowResult
import madngoding.hammad.moviecatalog.util.AppExecutors
import madngoding.hammad.moviecatalog.vo.Resource
import timber.log.Timber

class DataRepositoryImpl(private val remoteDataSource: RemoteDataSource,
                         private val localDataSource: LocalDataSource,
                         private val appExecutors: AppExecutors) : DataRepository {

    override fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResult>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.getMovie(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieResult>>> =
                    remoteDataSource.getMovie()

            public override fun saveCallResult(data: List<MovieResult>) {
                val movieList = ArrayList<MovieEntity>()
                for (each in data) {
                    val movie = MovieEntity(each.id.toString(),
                            each.title,
                            each.posterPath,
                            each.releaseDate,
                            each.overview,
                            false)
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(id: String): LiveData<Resource<MovieEntity>> {
        return object : DetailResource<MovieEntity>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getMovieWithId(id)
        }.asLiveData()
    }

    override fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResult>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.getTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShowResult>>> =
                    remoteDataSource.getTvShow()

            public override fun saveCallResult(data: List<TvShowResult>) {
                val tvShowList = ArrayList<TvShowEntity>()
                Timber.e(data.toString())
                for (each in data) {
                    val tvShow = TvShowEntity(each.id.toString(),
                            each.name,
                            each.posterPath,
                            each.firstAirDate,
                            each.overview,
                            false)
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getTvShowDetail(id: String): LiveData<Resource<TvShowEntity>> {
        return object : DetailResource<TvShowEntity>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> = localDataSource.getTvShowWithId(id)
        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) =
            appExecutors.diskIO().execute {
                localDataSource.setFavoriteMovie(movie, state)
            }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) =
            appExecutors.diskIO().execute {
                localDataSource.setFavoriteTvShow(tvShow, state)
            }
}