package madngoding.hammad.moviecatalog.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class Response<T>(
        val page: Int,
        val results: List<T>,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
)