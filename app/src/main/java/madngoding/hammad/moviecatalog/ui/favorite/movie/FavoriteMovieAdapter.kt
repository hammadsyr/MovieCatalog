package madngoding.hammad.moviecatalog.ui.favorite.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import madngoding.hammad.moviecatalog.BuildConfig
import madngoding.hammad.moviecatalog.R
import madngoding.hammad.moviecatalog.data.source.local.entity.MovieEntity
import madngoding.hammad.moviecatalog.databinding.ListFavMovieBinding
import madngoding.hammad.moviecatalog.ui.favorite.FavoriteFragmentDirections

class FavoriteMovieAdapter : PagedListAdapter<MovieEntity, FavoriteMovieAdapter.MovieViewHolder>(MovieDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.list_fav_movie,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position) as MovieEntity
        holder.bind(movie)
    }

    class MovieViewHolder(private val binding: ListFavMovieBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            binding.setClickListener { view ->
                navigateToDetail(movie.id, view)
            }
            with(binding) {
                Glide.with(itemView.context).load(BuildConfig.IMAGE_URL + movie.posterPath)
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_error)
                        )
                        .into(ivMovie)
                setMovie(movie)
                executePendingBindings()
            }
        }

        private fun navigateToDetail(id: String, view: View) {
            val direction = FavoriteFragmentDirections.actionFavoriteFragmentToMovieDetailFragment(id)
            view.findNavController().navigate(direction)
        }
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem == newItem

        }
    }
}




