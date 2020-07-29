package madngoding.hammad.moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import madngoding.hammad.moviecatalog.data.source.local.entity.MovieEntity
import madngoding.hammad.moviecatalog.data.source.local.entity.TvShowEntity
import madngoding.hammad.moviecatalog.vo.Resource

interface DataRepository {
    fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getMovieDetail(id: String): LiveData<Resource<MovieEntity>>
    fun getTvShowDetail(id: String): LiveData<Resource<TvShowEntity>>
    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>
    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>
    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)
    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)
}