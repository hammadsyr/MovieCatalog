package madngoding.hammad.moviecatalog.ui.tvshow

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
import madngoding.hammad.moviecatalog.data.source.local.entity.TvShowEntity
import madngoding.hammad.moviecatalog.databinding.ListTvShowBinding

class TvShowAdapter : PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(TvShowDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        return TvShowViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_tv_show, parent, false))
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position) as TvShowEntity
        holder.bind(tvShow)
    }

    class TvShowViewHolder(private val binding: ListTvShowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            binding.setClickListener { view ->
                navigateToDetail(tvShow.id, view)
            }

            with(binding) {
                Glide.with(itemView.context).load(BuildConfig.IMAGE_URL + tvShow.posterPath)
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_error)
                        )
                        .into(ivTvShow)
                setTvShow(tvShow)
                executePendingBindings()
            }
        }

        private fun navigateToDetail(id: String, view: View) {
            val direction = TvShowFragmentDirections.actionTvShowFragmentToDetailFragment(id)
            view.findNavController().navigate(direction)
        }
    }

    class TvShowDiffCallback : DiffUtil.ItemCallback<TvShowEntity>() {
        override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
            return oldItem == newItem

        }
    }
}



