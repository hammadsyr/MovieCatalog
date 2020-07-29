package madngoding.hammad.moviecatalog.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import madngoding.hammad.moviecatalog.databinding.FavoriteMovieFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment() {
    private lateinit var binding: FavoriteMovieFragmentBinding
    private val viewModel: FavoriteMovieViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FavoriteMovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            progressFavMovie.visibility = View.VISIBLE
            val movieAdapter = FavoriteMovieAdapter()
            viewModel.getFavoriteMovie().observe(viewLifecycleOwner, Observer { movies ->
                progressFavMovie.visibility = View.GONE
                movieAdapter.submitList(movies)
                movieAdapter.notifyDataSetChanged()
            })

            with(rvFavMovie) {
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}
