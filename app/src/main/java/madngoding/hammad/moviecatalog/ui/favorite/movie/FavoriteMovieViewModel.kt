package madngoding.hammad.moviecatalog.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import madngoding.hammad.moviecatalog.data.DataRepository
import madngoding.hammad.moviecatalog.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val dataRepository: DataRepository) : ViewModel() {
    private fun getFavoriteMovieRepo(): LiveData<PagedList<MovieEntity>> = dataRepository.getFavoriteMovie()
    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> = getFavoriteMovieRepo()
}