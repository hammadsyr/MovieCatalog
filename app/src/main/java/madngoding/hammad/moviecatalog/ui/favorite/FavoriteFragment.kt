package madngoding.hammad.moviecatalog.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import madngoding.hammad.moviecatalog.databinding.FavoriteFragmentBinding
import madngoding.hammad.moviecatalog.ui.main.MainActivity

class FavoriteFragment : Fragment() {
    private lateinit var binding: FavoriteFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = context?.let {
            FavoritePagerAdapter(it,
                    childFragmentManager
            )
        }

        binding.apply {
            vpFavorite.adapter = pagerAdapter
            tabFavorite.setupWithViewPager(vpFavorite)
        }

        context?.let {
            (it as MainActivity).supportActionBar?.elevation = 0f
        }

    }
}