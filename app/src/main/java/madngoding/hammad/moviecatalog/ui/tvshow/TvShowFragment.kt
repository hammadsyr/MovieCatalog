package madngoding.hammad.moviecatalog.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import madngoding.hammad.moviecatalog.databinding.TvShowFragmentBinding
import madngoding.hammad.moviecatalog.vo.Status
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private val viewModel: TvShowViewModel by viewModel()
    private lateinit var binding: TvShowFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = TvShowFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val tvShowAdapter = TvShowAdapter()
            viewModel.getTvShow().observe(viewLifecycleOwner, Observer { tvShows ->
                if (tvShows != null) {
                    when (tvShows.status) {
                        Status.LOADING -> progressTvShow.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            progressTvShow.visibility = View.GONE
                            tvShowAdapter.submitList(tvShows.data)
                            tvShowAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            progressTvShow.visibility = View.GONE
                            toast("An Error Occured")
                        }
                    }
                }
            })

            with(rvTvShow) {
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}
