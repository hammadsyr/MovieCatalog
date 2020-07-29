package madngoding.hammad.moviecatalog.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import madngoding.hammad.moviecatalog.data.source.local.entity.MovieEntity
import madngoding.hammad.moviecatalog.data.source.local.entity.TvShowEntity

@Dao
interface MainDao {

    @Query("SELECT * FROM movieentity")
    fun getMovie(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Query("SELECT * FROM tvshowentity")
    fun getTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<TvShowEntity>)

    @Query("SELECT * FROM movieentity WHERE id = :id")
    fun getMovieWithId(id: String): LiveData<MovieEntity>

    @Query("SELECT * FROM tvshowentity WHERE id = :id")
    fun getTvShowWithId(id: String): LiveData<TvShowEntity>

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM movieentity where is_favorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvshowentity where is_favorite = 1")
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity>
}