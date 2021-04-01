package com.example.moviesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.data.model.credits.Cast
import com.example.moviesapp.databinding.HorizontalListItemBinding

import com.example.moviesapp.presentation.details.DetailViewModel
import com.example.moviesapp.presentation.utils.RVAdapter
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.horizontal_list_item.view.*


class DetailsFragment : Fragment() {

    lateinit var detailViewModel: DetailViewModel
    val navArgs: DetailsFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = (activity as MainActivity).detailViewModel
        getDetails()
        loadView()
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
        override fun getLayoutId(position: Int, obj: Cast): Int {
            return R.layout.horizontal_list_item
        }

        override fun getMyViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val itemView = HorizontalListItemBinding.inflate(inflater)
            return MyViewHolder(itemView, viewType)
        }
    }


    fun getDetails() {
        if (navArgs.selectedTvShowID != null) {
            val selected_tvshowID = navArgs.selectedTvShowID!!
            detailViewModel.getTvShowDetail(selected_tvshowID)
            detailViewModel.getTvShowCredits(selected_tvshowID)
        }
        if (navArgs.selectedMovieID != null) {
            var selected_movieID = navArgs.selectedMovieID!!
            detailViewModel.getMovieDetail(selected_movieID)
            detailViewModel.getMovieCredits(selected_movieID)
        } else {
            Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    fun loadView() {
        if (navArgs.selectedTvShowID != null) {
            detailViewModel.tvShowDetail.observe(viewLifecycleOwner, {
                val tvShow = it?.data
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

        }
        if (navArgs.selectedMovieID != null) {
            detailViewModel.movieDetail.observe(viewLifecycleOwner, {
                val movie = it?.data
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
        }
        detailsRV.adapter = actorsAdapter
        detailsRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }

}