package madngoding.hammad.moviecatalog.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import madngoding.hammad.moviecatalog.data.DataRepository
import madngoding.hammad.moviecatalog.data.source.local.entity.TvShowEntity
import madngoding.hammad.moviecatalog.vo.Resource

class TvShowDetailViewModel(private val dataRepository: DataRepository, id: String) : ViewModel() {
    private val tvShowId = MutableLiveData<String>()

    init {
        this.tvShowId.value = id
    }

    private fun getTvShowDetailRepo(id: String) = dataRepository.getTvShowDetail(id)


    var getTvShowDetail: LiveData<Resource<TvShowEntity>> = Transformations.switchMap(tvShowId) { id ->
        getTvShowDetailRepo(id)
    }

    fun setFavorite() {
        val resource = getTvShowDetail.value
        if (resource != null) {
            val tvShowDetail = resource.data
            if (tvShowDetail != null) {
                val newState = !tvShowDetail.isFavorite
                dataRepository.setFavoriteTvShow(tvShowDetail, newState)
            }
        }
    }
}


