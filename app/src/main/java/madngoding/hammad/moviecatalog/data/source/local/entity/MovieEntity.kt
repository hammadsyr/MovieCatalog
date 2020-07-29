package madngoding.hammad.moviecatalog.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentity")
data class MovieEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        var id: String,

        @ColumnInfo(name = "title")
        var title: String?,

        @ColumnInfo(name = "poster_path")
        var posterPath: String?,

        @ColumnInfo(name = "release_date")
        var releaseDate: String?,

        @ColumnInfo(name = "overview")
        var overview: String?,

        @ColumnInfo(name = "is_favorite")
        var isFavorite: Boolean = false
)
