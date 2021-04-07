package com.example.moviesapp.presentation.details

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.MainActivity
import com.example.moviesapp.R
import com.example.moviesapp.data.model.apiResponse.MediaStatus
import com.example.moviesapp.data.model.apiResponse.Rated
import com.example.moviesapp.data.model.credits.Cast
import com.example.moviesapp.data.model.favorites.FavoriteReuqestBody
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.databinding.HorizontalListItemBinding
import com.example.moviesapp.presentation.utils.RVAdapter
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.horizontal_list_item.view.*
import kotlinx.coroutines.delay
import java.util.*
import kotlin.reflect.typeOf


class DetailsFragment : Fragment(R.layout.fragment_details) {

    lateinit var detailViewModel: DetailViewModel
    val navArgs: DetailsFragmentArgs by navArgs()
    var id: Int? = null
    var mediaType: String? = null
    var rating: Float ?= null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = (activity as MainActivity).detailViewModel
        val sessionId = (activity as MainActivity).sharedPreferences.getString("sessionId", null)

        loadMediaDetails(sessionId)
        loadRV()
        if (sessionId != null) {
            detailViewModel.getAuthInfo(sessionId)
            library_decimal_ratingbar.setOnRatingChangeListener { ratingBar, rating ->

                if (navArgs.selectedMovieID != null) {
                    var movieId = navArgs.selectedMovieID!!
                    sessionId.let {

                        detailViewModel.rateMovie(rating, movieId.toInt(), it)
                    }
                }
                if (navArgs.selectedTvShowID != null) {
                    var tvId = navArgs.selectedTvShowID!!
                    sessionId.let {
                        detailViewModel.rateTvShow(rating, tvId.toInt(), it)
                    }
                }
            }
            detailViewModel.movieState.observe(viewLifecycleOwner, {
                 setRatingStars(it)
            })
            detailViewModel.tvshowState.observe(viewLifecycleOwner, {
                setRatingStars(it)
            })

             setFavoriteIcon(sessionId)

            detailViewModel.authInfo.observe(viewLifecycleOwner, { auth ->

                if (auth.data != null) {
                    id?.let { mediaID ->
                        mediaType?.let { type ->
                            nonfavorite.setOnClickListener { it1 ->
                                nonFavoriteOnClick(mediaID, auth.data.id.toString(), sessionId!!, type)
                            }
                            favorite.setOnClickListener {
                                favoriteOnClick(mediaID, auth.data.id.toString(), sessionId!!, type)
                            }
                        }
                    }
                }

            })


        }
        shareImg.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "${titleText.text}  ${aboutText.text}")
            sendIntent.type = "text/plain"
            requireContext().startActivity(sendIntent)
        }

    }
fun setRatingStars(it: Resource<MediaStatus>){
    if (it.data?.rated!=null) {
        var numStr= it.data?.rated?.toString()?.replace("[^\\d.]".toRegex(), "")
        if (numStr?.length!! >0){
            library_decimal_ratingbar.rating = numStr.toFloat()
            library_decimal_ratingbar.progress = numStr.toFloat().toInt()
        }

    } else{
        library_decimal_ratingbar.progress = 2
    }
}
    override fun onResume() {
        super.onResume()
        var sessionId = (activity as MainActivity).sharedPreferences.getString("sessionId", null)
        setFavoriteIcon(sessionId)

    }

    override fun onStart() {
        super.onStart()
        var sessionId = (activity as MainActivity).sharedPreferences.getString("sessionId", null)
        setFavoriteIcon(sessionId)

    }

    fun setFavoriteIcon(sessionId: String?) {
        if (sessionId != null) {
            detailViewModel.movieState.observe(viewLifecycleOwner, {
                if (it.data != null) {
                    val state = it.data
                    if (state.favorite == false) {
                        nonfavorite.visibility = View.VISIBLE
                        favorite.visibility = View.GONE
                    } else {
                        favorite.visibility = View.VISIBLE
                        nonfavorite.visibility = View.GONE
                    }
                }
            })
            detailViewModel.tvshowState.observe(viewLifecycleOwner, {
                if (it.data != null) {
                    val state = it.data
                    if (state.favorite == false) {
                        nonfavorite.visibility = View.VISIBLE
                        favorite.visibility = View.GONE
                    } else {
                        favorite.visibility = View.VISIBLE
                        nonfavorite.visibility = View.GONE
                    }

                }
            })
        } else {
            nonfavorite.visibility = View.GONE
            favorite.visibility = View.GONE
        }
    }

    fun nonFavoriteOnClick(id: Int, accountId: String, sessionId: String, mediaType: String) {
        nonfavorite.setOnClickListener {
            detailViewModel.toggleFavorite(FavoriteReuqestBody(mediaType, id, true), accountId, sessionId)
            nonfavorite.visibility = View.GONE
            favorite.visibility = View.VISIBLE
        }
    }

    fun favoriteOnClick(id: Int, accountId: String, sessionId: String, mediaType: String) {
        favorite.setOnClickListener {
            detailViewModel.toggleFavorite(FavoriteReuqestBody(mediaType, id, false), accountId, sessionId)
            nonfavorite.visibility = View.VISIBLE
            favorite.visibility = View.GONE
        }
    }

    class MyViewHolder(itemView: HorizontalListItemBinding, viewType: Int) : RecyclerView.ViewHolder(itemView.root),
            RVAdapter.Binder<Cast> {
        override fun bind(cast: Cast) {
            itemView.setOnClickListener {
                var action = DetailsFragmentDirections.actionDetailsFragmentToActorDetailsFragment(cast.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
            itemView.titletext.text = cast.name
            itemView.votetext?.visibility = View.GONE
            Glide.with(itemView.posterImg.context)
                    .load("https://image.tmdb.org/t/p/w185/${cast.profilePath}")
                    .error(R.drawable.ic_baseline_looks_one_24)
                    .into(itemView.posterImg)
        }

    }

    val actorsAdapter = object : RVAdapter<Cast>() {
        override fun setLayout(position: Int, obj: Cast): Int {
            return R.layout.horizontal_list_item
        }

        override fun getMyViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val itemView = HorizontalListItemBinding.inflate(inflater)
            return MyViewHolder(itemView, viewType)
        }
    }


    fun loadMediaDetails(sessionId: String?) = if (navArgs.selectedTvShowID != null) {
        val selected_tvshowID = navArgs.selectedTvShowID!!
        detailViewModel.getTvShowDetail(selected_tvshowID)
        detailViewModel.getTvShowCredits(selected_tvshowID)
        detailViewModel.tvShowDetail.observe(viewLifecycleOwner, {
            val tvShow = it?.data
            if (tvShow != null && sessionId != null) {
                detailViewModel.getTvShowState(tvShow.id, sessionId)
                id = tvShow.id
                mediaType = "tv"
            }
            titleText.text = tvShow?.name
            voteText.text = "IMDB: " + tvShow?.voteAverage.toString()
            aboutText.text = tvShow?.overview
            durationText.text = " Season: ${tvShow?.numberOfSeasons}  Episode: ${tvShow?.numberOfEpisodes}"
            Glide.with(poster.context)
                    .load("https://image.tmdb.org/t/p/w500/${tvShow?.posterPath}")
                    .error(R.drawable.ic_baseline_looks_one_24)
                    .into(poster)

        })
        detailViewModel.tvShowCredits.observe(viewLifecycleOwner, {
            actorsAdapter.differ.submitList(it.data?.cast)
        })
    } else if (navArgs.selectedMovieID != null) {
        var selected_movieID = navArgs.selectedMovieID!!
        detailViewModel.getMovieDetail(selected_movieID)
        detailViewModel.getMovieCredits(selected_movieID)
        detailViewModel.movieDetail.observe(viewLifecycleOwner, { it ->
            val movie = it.data
            if (movie != null && sessionId != null) {
                detailViewModel.getMovieState(movie.id, sessionId)
                id = movie.id
                mediaType = "movie"
            }

            titleText.text = movie?.title
            voteText.text = "IMDB: " + movie?.voteAverage.toString()
            aboutText.text = movie?.overview
            durationText.text = movie?.runtime.toString() + " minutes"
            Glide.with(poster.context)
                    .load("https://image.tmdb.org/t/p/original/${movie?.posterPath}")
                    .error(R.drawable.ic_baseline_looks_one_24)
                    .into(poster)
        })
        detailViewModel.movieCredits.observe(viewLifecycleOwner, {
            actorsAdapter.differ.submitList(it.data?.cast)
        })
    } else {
        Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
    }

    fun loadRV() {
        detailsRV.adapter = actorsAdapter
        detailsRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }


}