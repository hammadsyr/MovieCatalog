package madngoding.hammad.moviecatalog.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import madngoding.hammad.moviecatalog.data.DataRepository
import madngoding.hammad.moviecatalog.data.source.local.entity.MovieEntity
import madngoding.hammad.moviecatalog.vo.Resource

class MovieViewModel(private val dataRepository: DataRepository) : ViewModel() {
    private fun getMovieRepo() = dataRepository.getMovie()
    fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>> = getMovieRepo()
}
