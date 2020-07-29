package madngoding.hammad.moviecatalog.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import madngoding.hammad.moviecatalog.data.source.remote.response.MovieResult
import madngoding.hammad.moviecatalog.data.source.remote.response.TvShowResult
import madngoding.hammad.moviecatalog.util.API
import madngoding.hammad.moviecatalog.util.EspressoIdlingResource
import madngoding.hammad.moviecatalog.util.RetrofitClient
import madngoding.hammad.moviecatalog.util.Service
import retrofit2.Retrofit

class RemoteDataSource {

    private val retrofit: Retrofit = RetrofitClient.instance
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val jsonApi: Service = retrofit.create(Service::class.java)

    fun getMovie(): LiveData<ApiResponse<List<MovieResult>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<MovieResult>>>()
        compositeDisposable.add(
                jsonApi.getMovie(API.API_KEY, API.LANGUAGE, API.PAGE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ data ->
                            data.let {
                                result.value = ApiResponse.success(it.results)
                                EspressoIdlingResource.decrement()
                            }
                        }, {
                            it.message?.let { it1 ->
                                val list = ArrayList<MovieResult>()
                                result.value = ApiResponse.error(it1, list)
                                EspressoIdlingResource.decrement()
                            }
                        })
        )
        return result
    }

    fun getTvShow(): LiveData<ApiResponse<List<TvShowResult>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<TvShowResult>>>()
        compositeDisposable.add(
                jsonApi.getTvShow(API.API_KEY, API.LANGUAGE, API.PAGE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ data ->
                            data.let {
                                result.value = ApiResponse.success(it.results)
                                EspressoIdlingResource.decrement()
                            }
                        }, {
                            it.message?.let { it1 ->
                                val list = ArrayList<TvShowResult>()
                                result.value = ApiResponse.error(it1, list)
                                EspressoIdlingResource.decrement()
                            }
                        })
        )
        return result
    }
}