package madngoding.hammad.moviecatalog.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import madngoding.hammad.moviecatalog.data.source.local.entity.MovieEntity
import madngoding.hammad.moviecatalog.data.source.local.entity.TvShowEntity
import madngoding.hammad.moviecatalog.data.source.local.room.MainDao

class LocalDataSource(private val mainDao: MainDao) {
    fun getMovie(): DataSource.Factory<Int, MovieEntity> = mainDao.getMovie()
    fun getTvShow(): DataSource.Factory<Int, TvShowEntity> = mainDao.getTvShow()

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> =
            mainDao.getFavoriteMovie()

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity> =
            mainDao.getFavoriteTvShow()

    fun getMovieWithId(id: String): LiveData<MovieEntity> =
            mainDao.getMovieWithId(id)

    fun getTvShowWithId(id: String): LiveData<TvShowEntity> =
            mainDao.getTvShowWithId(id)

    fun insertMovie(movie: List<MovieEntity>) {
        mainDao.insertMovie(movie)
    }

    fun insertTvShow(tvShow: List<TvShowEntity>) {
        mainDao.insertTvShow(tvShow)
    }

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mainDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mainDao.updateTvShow(tvShow)
    }
}