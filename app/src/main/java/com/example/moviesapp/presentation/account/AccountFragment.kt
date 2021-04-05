package com.example.moviesapp.presentation.account

import android.content.Context
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.MainActivity

import com.example.moviesapp.R
import com.example.moviesapp.data.model.movie.Movie
import com.example.moviesapp.data.model.tvshow.TvShow
import com.example.moviesapp.databinding.ListItemBinding
import com.example.moviesapp.presentation.movie.MoviesFragmentDirections
import com.example.moviesapp.presentation.utils.RVAdapter
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.list_item.view.*

class AccountFragment : Fragment() {
    lateinit var accountViewModel: AccountViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    var list = mutableListOf<Any>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var activity = activity as MainActivity
        var sessionId = activity.sharedPreferences.getString("sessionId", null)
        accountViewModel = (activity).accountViewModel

        if (sessionId != null) {
            accountViewModel.getAccountInfo(sessionId)
            getMediaLists(sessionId)


        signoutBtn.setOnClickListener {
            if (sessionId != null) {
                accountViewModel.signOut(sessionId)
            }
            accountViewModel.sessionStatus.observe(viewLifecycleOwner, {
                if (it.data?.success == true) {
                    findNavController().navigate(R.id.logInFragment)
                } else {
                    Toast.makeText(context, "Log Out", Toast.LENGTH_SHORT).show()
                }
            })
        }
        loadFavoriteMoviesRV()
        loadFavoriteTvShowRV()
        favoriteTvsLabel.setOnClickListener {
            var visibility = tvshowRV.visibility
            if (visibility == View.GONE) {
                tvshowRV.visibility = View.VISIBLE
                movieRV.visibility = View.GONE
            }
            else{
                tvshowRV.visibility = View.GONE
            }

        }
        favoriteMoviesLabel.setOnClickListener {
            var visibility = movieRV.visibility
            if (visibility == View.GONE) {
                movieRV.visibility = View.VISIBLE
                tvshowRV.visibility=View.GONE
            }
            else{
                movieRV.visibility = View.GONE
            }

        }


}

}

    override fun onResume() {
        super.onResume()
        var sessionId = (activity as MainActivity).sharedPreferences.getString("sessionId", null)
        if (sessionId != null) {
            getMediaLists(sessionId)
        }
    }

    override fun onStart() {
        super.onStart()
        var sessionId = (activity as MainActivity).sharedPreferences.getString("sessionId", null)
        if (sessionId != null) {
            getMediaLists(sessionId)
        }
    }
    fun getMediaLists(sessionId:String){
        accountViewModel.accountInfo.observe(viewLifecycleOwner, {
            if (it.data != null) {
                var accountId = it.data.id.toString()
                activity?.getSharedPreferences("accountId", Context.MODE_PRIVATE)?.edit()?.putString("accountId", accountId)?.apply()
                userInfoText.text = it.data.username
                accountViewModel.getMoviesList(accountId, sessionId)
                accountViewModel.getFavoritesList(accountId, sessionId)

            }
        })}
    class MovieHolder(itemView: ListItemBinding, viewType: Int) : RecyclerView.ViewHolder(itemView.root),
            RVAdapter.Binder<Movie> {
        override fun bind(movie: Movie) {
            itemView.titleTV.text = movie.title
            itemView.mediaTypeText.visibility = TextView.INVISIBLE
            itemView.voteTV?.text = movie.voteAverage.toString()
            Glide.with(itemView.imageView.context)
                    .load("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
                    .error(R.drawable.ic_baseline_close_24)
                    .into(itemView.imageView)
        }

    }

    class TvShowHolder(itemView: ListItemBinding, viewType: Int) : RecyclerView.ViewHolder(itemView.root),
            RVAdapter.Binder<TvShow> {
        override fun bind(tvShow: TvShow) {
            itemView.titleTV.text = tvShow.name
            itemView.mediaTypeText.visibility = TextView.INVISIBLE
            itemView.voteTV?.text = tvShow.voteAverage.toString()
            Glide.with(itemView.imageView.context)
                    .load("https://image.tmdb.org/t/p/w185/${tvShow.posterPath}")
                    .error(R.drawable.ic_baseline_close_24)
                    .into(itemView.imageView)
        }

    }

    val favoriteMovies = object : RVAdapter<Movie>() {
        override fun setLayout(position: Int, obj: Movie): Int {
            return R.layout.list_item


        }

        override fun getMyViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val listItemView = ListItemBinding.inflate(inflater)
            return MovieHolder(listItemView, viewType)
        }
    }
    val favoriteTvShows = object : RVAdapter<TvShow>() {
        override fun setLayout(position: Int, obj: TvShow): Int {
            return R.layout.list_item


        }

        override fun getMyViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val listItemView = ListItemBinding.inflate(inflater)
            return TvShowHolder(listItemView, viewType)
        }
    }

    fun loadFavoriteMoviesRV() {
        accountViewModel.favoriteMovies.observeForever {
            if (it.data != null) {
                favoriteMovies.differ.submitList(it.data.results)

            }
        }
        movieRV.adapter = favoriteMovies
        movieRV.layoutManager = LinearLayoutManager(context)
    }

    fun loadFavoriteTvShowRV() {
        accountViewModel.favoriteTvShow.observeForever {
            if (it.data != null) {
                favoriteTvShows.differ.submitList(it.data.results)

            }
        }
        tvshowRV.adapter = favoriteTvShows
        tvshowRV.layoutManager = LinearLayoutManager(context)

    }
}