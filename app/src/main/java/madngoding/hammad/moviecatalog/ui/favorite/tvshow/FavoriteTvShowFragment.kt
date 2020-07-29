package madngoding.hammad.moviecatalog.ui.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import madngoding.hammad.moviecatalog.databinding.FavoriteTvShowFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteTvShowFragment : Fragment() {
    private lateinit var binding: FavoriteTvShowFragmentBinding
    private val viewModel: FavoriteTvShowViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FavoriteTvShowFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            progressFavTvShow.visibility = View.VISIBLE
            val tvShowAdapter = FavoriteTvShowAdapter()
            viewModel.getFavoriteTvShow().observe(viewLifecycleOwner, Observer { tvShows ->
                progressFavTvShow.visibility = View.GONE
                tvShowAdapter.submitList(tvShows)
                tvShowAdapter.notifyDataSetChanged()
            })

            with(rvFavTvShow) {
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}
