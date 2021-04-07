package com.example.moviesapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.presentation.account.AccountViewModel
import com.example.moviesapp.presentation.account.AccountViewModelFactory
import com.example.moviesapp.presentation.actorDetails.ActorDetailViewModel
import com.example.moviesapp.presentation.actorDetails.ActorDetailViewModelFactory
import com.example.moviesapp.presentation.auth.AuthViewModel
import com.example.moviesapp.presentation.auth.AuthViewModelFactory
import com.example.moviesapp.presentation.details.DetailViewModel
import com.example.moviesapp.presentation.details.DetailViewModelFactory
import com.example.moviesapp.presentation.login.LogInFragment
import com.example.moviesapp.presentation.login.LogInViewModel
import com.example.moviesapp.presentation.login.LogInViewModelFactory
import com.example.moviesapp.presentation.map.MapViewModel
import com.example.moviesapp.presentation.map.MapViewModelFactory
import com.example.moviesapp.presentation.movie.MovieViewModel
import com.example.moviesapp.presentation.movie.MovieViewModelFactory
import com.example.moviesapp.presentation.search.SearchViewModel
import com.example.moviesapp.presentation.search.SearchViewModelFactory
import com.example.moviesapp.presentation.tvshow.TvShowViewModel
import com.example.moviesapp.presentation.tvshow.TvShowViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: MovieViewModelFactory
    lateinit var viewmodel: MovieViewModel

    @Inject
    lateinit var tvshowFactory: TvShowViewModelFactory
    lateinit var tvShowViewModel: TvShowViewModel

    @Inject
    lateinit var detailFactory: DetailViewModelFactory
    lateinit var detailViewModel: DetailViewModel

    @Inject
    lateinit var actorFactory: ActorDetailViewModelFactory
    lateinit var actorViewModel: ActorDetailViewModel

    @Inject
    lateinit var searchFactory: SearchViewModelFactory
    lateinit var searchViewModel: SearchViewModel

    @Inject
    lateinit var loginFactory: LogInViewModelFactory
    lateinit var logInViewModel: LogInViewModel


    @Inject
    lateinit var authFactory: AuthViewModelFactory
    lateinit var authViewModel: AuthViewModel

    @Inject
    lateinit var accountFactory: AccountViewModelFactory
    lateinit var accountViewModel: AccountViewModel

    @Inject
    lateinit var mapFactory: MapViewModelFactory
    lateinit var mapViewModel: MapViewModel


    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)



        if (Locale.getDefault().equals(Locale.ENGLISH) ) {

            toolbar.setTitle(resources.getString(R.string.toolBar))
        } else {
            var config = resources.configuration
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
                config.setLocale(Locale("tr"))
                toolbar.setTitle(resources.getString(R.string.toolBar))
            }
        }
        setSupportActionBar(toolbar)
        viewmodel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)
        tvShowViewModel = ViewModelProvider(this, tvshowFactory).get(TvShowViewModel::class.java)
        detailViewModel = ViewModelProvider(this, detailFactory).get(DetailViewModel::class.java)
        actorViewModel = ViewModelProvider(this, actorFactory).get(ActorDetailViewModel::class.java)
        searchViewModel = ViewModelProvider(this, searchFactory).get(SearchViewModel::class.java)
        logInViewModel = ViewModelProvider(this, loginFactory).get(LogInViewModel::class.java)
        accountViewModel = ViewModelProvider(this, accountFactory).get(AccountViewModel::class.java)
        mapViewModel = ViewModelProvider(this, mapFactory).get(MapViewModel::class.java)
        authViewModel = ViewModelProvider(this, authFactory).get(AuthViewModel::class.java)

        sharedPreferences = this.getSharedPreferences("sessionId", MODE_PRIVATE)

        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.logInFragment, R.id.authFragment -> {
                    bottomNavigationView2.visibility = View.GONE
                    toolbar.visibility = View.GONE
                }
                R.id.moviesFragment -> {
                    toolbar.visibility = View.VISIBLE
                    bottomNavigationView2.visibility = View.VISIBLE
                    toolbar.menu.findItem(R.id.mapItem)?.isVisible = true

                }

                else -> {
                    bottomNavigationView2.visibility = View.VISIBLE
                    toolbar.menu.findItem(R.id.mapItem)?.isVisible = false
                }

            }


        }

        bottomNavigationView2.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mapItem -> NavHostFragment.findNavController(my_nav_host_fragment).navigate(R.id.mapsFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }

}