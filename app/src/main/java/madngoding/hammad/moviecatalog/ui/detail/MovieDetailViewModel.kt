package madngoding.hammad.moviecatalog.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import madngoding.hammad.moviecatalog.data.DataRepository
import madngoding.hammad.moviecatalog.data.source.local.entity.MovieEntity
import madngoding.hammad.moviecatalog.vo.Resource

class MovieDetailViewModel(private val dataRepository: DataRepository, id: String) : ViewModel() {
    private val movieId = MutableLiveData<String>()

    init {
        this.movieId.value = id
    }

    private fun getMovieDetailRepo(id: String) = dataRepository.getMovieDetail(id)

    var getMovieDetail: LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { id ->
        getMovieDetailRepo(id)
    }

    fun setFavorite() {
        val resource = getMovieDetail.value
        if (resource != null) {
            val movieDetail = resource.data
            if (movieDetail != null) {
                val newState = !movieDetail.isFavorite
                dataRepository.setFavoriteMovie(movieDetail, newState)
            }
        }
    }
}