package madngoding.hammad.moviecatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResult(
        val id: Int,
        val overview: String,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
        val title: String
)