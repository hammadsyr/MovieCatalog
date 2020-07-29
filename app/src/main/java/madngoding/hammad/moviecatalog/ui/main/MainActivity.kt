package madngoding.hammad.moviecatalog.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import madngoding.hammad.moviecatalog.R
import madngoding.hammad.moviecatalog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navController = findNavController(R.id.nav_host_main)
        nav_view.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.movieFragment ||
                    destination.id == R.id.tvShowFragment ||
                    destination.id == R.id.favoriteFragment
            ) {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                nav_view.visibility = View.VISIBLE
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                nav_view.visibility = View.GONE
            }
        }
    }
}