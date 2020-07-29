package madngoding.hammad.moviecatalog.ui.detail

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import madngoding.hammad.moviecatalog.BuildConfig
import madngoding.hammad.moviecatalog.R
import madngoding.hammad.moviecatalog.data.source.local.entity.TvShowEntity
import madngoding.hammad.moviecatalog.databinding.TvShowDetailFragmentBinding
import madngoding.hammad.moviecatalog.vo.Status
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class TvShowDetailFragment : Fragment() {
    private lateinit var binding: TvShowDetailFragmentBinding
    private val args: TvShowDetailFragmentArgs by navArgs()
    private var id = ""
    private lateinit var viewModel: TvShowDetailViewModel
    private lateinit var menu: Menu

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TvShowDetailFragmentBinding.inflate(inflater, container, false)
        id = args.id
        viewModel = getViewModel { parametersOf(id) }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.getTvShowDetail.observe(viewLifecycleOwner, Observer { response ->
                when (response.status) {
                    Status.LOADING -> {
                        progressDetail.visibility = View.VISIBLE
                        clDetail.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        progressDetail.visibility = View.GONE
                        clDetail.visibility = View.VISIBLE
                        val tvShow = response.data as TvShowEntity
                        context?.let {
                            Glide.with(it).load(BuildConfig.IMAGE_URL + tvShow.posterPath)
                                    .apply(
                                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                                    .error(R.drawable.ic_error)
                                    )
                                    .into(ivDetail)
                        }
                        tvTitleDetail.text = tvShow.name
                        tvDateDetail.text = tvShow.firstAirDate
                        tvOverviewDetail.text = tvShow.overview
                    }
                    Status.ERROR -> {
                        progressDetail.visibility = View.GONE
                        clDetail.visibility = View.VISIBLE
                        toast("An Error Occured")
                    }
                }

            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        this.menu = menu
        viewModel.getTvShowDetail.observe(viewLifecycleOwner, Observer { response ->
            binding.apply {
                when (response.status) {
                    Status.LOADING -> {
                        progressDetail.visibility = View.VISIBLE
                        clDetail.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        progressDetail.visibility = View.GONE
                        clDetail.visibility = View.VISIBLE
                        val tvShow = response.data as TvShowEntity
                        val isFavorite = tvShow.isFavorite
                        setFavoriteState(isFavorite)
                    }
                    Status.ERROR -> {
                        progressDetail.visibility = View.GONE
                        clDetail.visibility = View.VISIBLE
                        toast("An Error Occured")
                    }

                }
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favoriteAction -> {
                viewModel.setFavorite()
                return true
            }
            android.R.id.home -> {
                findNavController().navigateUp()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu.findItem(R.id.favoriteAction)
        context?.let { context ->
            if (state) {
                menuItem?.icon = ContextCompat.getDrawable(context, R.drawable.ic_favorited)
            } else {
                menuItem?.icon = ContextCompat.getDrawable(context, R.drawable.ic_favorite)
            }
        }
    }
}
