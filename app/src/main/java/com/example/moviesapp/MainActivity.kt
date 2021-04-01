package com.example.moviesapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.*
import com.example.moviesapp.data.model.movie.Movie
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.databinding.ListItemBinding
import com.example.moviesapp.presentation.map.MapViewModel
import com.example.moviesapp.presentation.map.MapViewModelFactory
import com.example.moviesapp.presentation.account.AccountViewModel
import com.example.moviesapp.presentation.account.AccountViewModelFactory
import com.example.moviesapp.presentation.actorDetails.ActorDetailViewModel
import com.example.moviesapp.presentation.actorDetails.ActorDetailViewModelFactory
import com.example.moviesapp.presentation.details.DetailViewModel
import com.example.moviesapp.presentation.details.DetailViewModelFactory
import com.example.moviesapp.presentation.login.LogInViewModel
import com.example.moviesapp.presentation.login.LogInViewModelFactory
import com.example.moviesapp.presentation.movie.MovieViewModel
import com.example.moviesapp.presentation.movie.MovieViewModelFactory
import com.example.moviesapp.presentation.search.SearchViewModel
import com.example.moviesapp.presentation.search.SearchViewModelFactory
import com.example.moviesapp.presentation.signup.SignUpViewModel
import com.example.moviesapp.presentation.signup.SignUpViewModelFactory
import com.example.moviesapp.presentation.tvshow.TvShowViewModel
import com.example.moviesapp.presentation.tvshow.TvShowViewModelFactory
import com.example.moviesapp.presentation.utils.RVAdapter
import com.google.api.Context
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
import io.grpc.InternalChannelz.id
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.list_item.view.*
import retrofit2.Retrofit
import java.io.*
import javax.inject.Inject
import javax.inject.Named
import kotlin.math.sign


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
    lateinit var signupFactory: SignUpViewModelFactory
    lateinit var signUpViewModel: SignUpViewModel

    @Inject
    lateinit var accountFactory: AccountViewModelFactory
    lateinit var accountViewModel: AccountViewModel

    @Inject
    lateinit var mapFactory: MapViewModelFactory
    lateinit var mapViewModel: MapViewModel


    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(applicationContext)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(toolbar)
        viewmodel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)
        tvShowViewModel = ViewModelProvider(this, tvshowFactory).get(TvShowViewModel::class.java)
        detailViewModel = ViewModelProvider(this, detailFactory).get(DetailViewModel::class.java)
        actorViewModel = ViewModelProvider(this, actorFactory).get(ActorDetailViewModel::class.java)
        searchViewModel = ViewModelProvider(this, searchFactory).get(SearchViewModel::class.java)
        logInViewModel = ViewModelProvider(this, loginFactory).get(LogInViewModel::class.java)
        signUpViewModel = ViewModelProvider(this, signupFactory).get(SignUpViewModel::class.java)
        accountViewModel = ViewModelProvider(this, accountFactory).get(AccountViewModel::class.java)
        mapViewModel = ViewModelProvider(this, mapFactory).get(MapViewModel::class.java)


        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.logInFragment, R.id.signUpFragment -> {
                    bottomNavigationView2.visibility = View.INVISIBLE
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


}
