package madngoding.hammad.moviecatalog.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import madngoding.hammad.moviecatalog.databinding.MovieFragmentBinding
import madngoding.hammad.moviecatalog.vo.Status
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModel()
    private lateinit var binding: MovieFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val movieAdapter = MovieAdapter()
            viewModel.getMovie().observe(viewLifecycleOwner, Observer { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> progressMovie.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            Timber.e(movies.toString())
                            progressMovie.visibility = View.GONE
                            movieAdapter.submitList(movies.data)
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            progressMovie.visibility = View.GONE
                            toast("An Error Occured")
                        }
                    }
                }
            })

            with(rvMovie) {
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

}
