package madngoding.hammad.moviecatalog.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import madngoding.hammad.moviecatalog.data.DataRepository
import madngoding.hammad.moviecatalog.data.source.local.entity.TvShowEntity
import madngoding.hammad.moviecatalog.vo.Resource

class TvShowViewModel(private val dataRepository: DataRepository) : ViewModel() {
    private fun getTvShowRepo() = dataRepository.getTvShow()
    fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> = getTvShowRepo()
}
