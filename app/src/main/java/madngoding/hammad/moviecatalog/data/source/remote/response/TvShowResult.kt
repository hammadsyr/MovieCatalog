package madngoding.hammad.moviecatalog.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class TvShowResult(
        @SerializedName("first_air_date")
        val firstAirDate: String,
        val id: Int,
        val name: String,
        val overview: String,
        @SerializedName("poster_path")
        val posterPath: String
)