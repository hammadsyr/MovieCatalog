package madngoding.hammad.moviecatalog.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowentity")
data class TvShowEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        var id: String,

        @ColumnInfo(name = "name")
        var name: String?,

        @ColumnInfo(name = "poster_path")
        var posterPath: String?,

        @ColumnInfo(name = "first_air_date")
        var firstAirDate: String?,

        @ColumnInfo(name = "overview")
        var overview: String?,

        @ColumnInfo(name = "is_favorite")
        var isFavorite: Boolean = false
)