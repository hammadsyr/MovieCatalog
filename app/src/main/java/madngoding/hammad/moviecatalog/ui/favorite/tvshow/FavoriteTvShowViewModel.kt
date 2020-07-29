package madngoding.hammad.moviecatalog.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import madngoding.hammad.moviecatalog.data.DataRepository
import madngoding.hammad.moviecatalog.data.source.local.entity.TvShowEntity

class FavoriteTvShowViewModel(private val dataRepository: DataRepository) : ViewModel() {
    private fun getFavoriteTvShowRepo(): LiveData<PagedList<TvShowEntity>> = dataRepository.getFavoriteTvShow()
    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> = getFavoriteTvShowRepo()
}