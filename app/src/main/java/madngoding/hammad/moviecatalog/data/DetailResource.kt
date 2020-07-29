package madngoding.hammad.moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import madngoding.hammad.moviecatalog.util.AppExecutors
import madngoding.hammad.moviecatalog.vo.Resource

abstract class DetailResource<RequestType>(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<RequestType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { newData ->
            result.value = Resource.success(newData)
        }
    }

    protected abstract fun loadFromDB(): LiveData<RequestType>

    fun asLiveData(): LiveData<Resource<RequestType>> = result
}