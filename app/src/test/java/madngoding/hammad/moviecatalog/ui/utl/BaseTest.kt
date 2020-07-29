package madngoding.hammad.moviecatalog.ui.utl

import com.google.gson.Gson
import madngoding.hammad.moviecatalog.data.source.remote.response.MovieResult
import madngoding.hammad.moviecatalog.data.source.remote.response.Response
import madngoding.hammad.moviecatalog.data.source.remote.response.TvShowResult
import java.io.File

object BaseTest {
    private fun getJson(path: String): String {
        val uri = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

    fun getLocalMovie(): List<MovieResult> {
        val jsonMovie = getJson("popular_movie_list.json")
        val movieObject = Gson().fromJson<Response<List<MovieResult>>>(jsonMovie, Response::class.java)
        val movieListArray = Gson().toJsonTree(movieObject.results).asJsonArray
        return Gson().fromJson<List<MovieResult>>(movieListArray, List::class.java)
    }

    fun getLocalTvShow(): List<TvShowResult> {
        val jsonTvShow = getJson("popular_tv_show_list.json")
        val tvShowObject = Gson().fromJson<Response<List<TvShowResult>>>(jsonTvShow, Response::class.java)
        val tvShowListArray = Gson().toJsonTree(tvShowObject.results).asJsonArray
        return Gson().fromJson<List<TvShowResult>>(tvShowListArray, List::class.java)
    }
}