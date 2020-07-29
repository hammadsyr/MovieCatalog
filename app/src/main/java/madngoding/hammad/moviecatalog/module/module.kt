package madngoding.hammad.moviecatalog.module

import madngoding.hammad.moviecatalog.data.DataRepository
import madngoding.hammad.moviecatalog.data.DataRepositoryImpl
import madngoding.hammad.moviecatalog.data.source.local.LocalDataSource
import madngoding.hammad.moviecatalog.data.source.local.room.MainDatabase
import madngoding.hammad.moviecatalog.data.source.remote.RemoteDataSource
import madngoding.hammad.moviecatalog.ui.detail.MovieDetailViewModel
import madngoding.hammad.moviecatalog.ui.detail.TvShowDetailViewModel
import madngoding.hammad.moviecatalog.ui.favorite.movie.FavoriteMovieViewModel
import madngoding.hammad.moviecatalog.ui.favorite.tvshow.FavoriteTvShowViewModel
import madngoding.hammad.moviecatalog.ui.movie.MovieViewModel
import madngoding.hammad.moviecatalog.ui.tvshow.TvShowViewModel
import madngoding.hammad.moviecatalog.util.AppExecutors
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {
    viewModel { MovieViewModel(get()) }
    viewModel { (id: String) -> TvShowDetailViewModel(get(), id) }
    viewModel { (id: String) -> MovieDetailViewModel(get(), id) }
    viewModel { TvShowViewModel(get()) }
    viewModel { FavoriteTvShowViewModel(get()) }
    viewModel { FavoriteMovieViewModel(get()) }

    factory<DataRepository> { DataRepositoryImpl(get(), get(), get()) }
    factory { RemoteDataSource() }
    factory { LocalDataSource(get()) }
    factory { MainDatabase.getInstance(get()).mainDao() }
    factory { AppExecutors() }
}