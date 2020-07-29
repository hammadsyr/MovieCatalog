package madngoding.hammad.moviecatalog.util

import io.reactivex.Observable
import madngoding.hammad.moviecatalog.data.source.remote.response.MovieResult
import madngoding.hammad.moviecatalog.data.source.remote.response.Response
import madngoding.hammad.moviecatalog.data.source.remote.response.TvShowResult
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("${API.MOVIE}/${API.POPULAR}")
    fun getMovie(@Query("api_key") apiKey: String,
                 @Query("language") language: String,
                 @Query("page") page: String): Observable<Response<MovieResult>>

    @GET("${API.TV_SHOW}/${API.POPULAR}")
    fun getTvShow(@Query("api_key") apiKey: String,
                  @Query("language") language: String,
                  @Query("page") page: String): Observable<Response<TvShowResult>>
}
