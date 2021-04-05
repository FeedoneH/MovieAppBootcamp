package com.example.moviesapp.presentation.actorDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.MainActivity
import com.example.moviesapp.R
import com.example.moviesapp.data.model.actor.Filmography
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.databinding.ListItemBinding
import com.example.moviesapp.presentation.utils.RVAdapter
import kotlinx.android.synthetic.main.fragment_actor_details.*
import kotlinx.android.synthetic.main.list_item.view.*

class ActorDetailsFragment : Fragment() {
    val actorDetailsArgs: ActorDetailsFragmentArgs by navArgs()

    lateinit var actorViewModel: ActorDetailViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_actor_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actorViewModel = (activity as MainActivity).actorViewModel

        getActorsData()
        loadView()
      
    }

    class MyViewHolder(itemView: ListItemBinding, viewType: Int) : RecyclerView.ViewHolder(itemView.root),
            RVAdapter.Binder<Filmography> {

        override fun bind(media: Filmography) {

            itemView.setOnClickListener {
                var movieID = if(media.mediaType=="movie")  media.id.toString() else null
                var tvShowID = if(media.mediaType=="tv")  media.id.toString() else null

                val action = ActorDetailsFragmentDirections.actionActorDetailsFragmentToDetailsFragment(movieID, tvShowID)
                Navigation.findNavController(it).navigate(action)
            }
            var name = if(media.mediaType=="movie")  media.title else media.name
            itemView.titleTV.text = name
            itemView.voteTV?.text = media.voteAverage.toString()
            itemView.mediaTypeText.visibility = TextView.INVISIBLE
            Glide.with(itemView.imageView.context)
                    .load("https://image.tmdb.org/t/p/w185/${media.posterPath}")
                    .error(R.drawable.ic_baseline_looks_one_24)
                    .into(itemView.imageView)
        }

    }

    val actorCreditsAdapter = object : RVAdapter<Filmography>() {
        override fun setLayout(position: Int, obj: Filmography): Int {
            return R.layout.list_item
        }

        override fun getMyViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val itemView = ListItemBinding.inflate(inflater)
            return MyViewHolder(itemView, viewType)
        }
    }

    fun getActorsData() {
        var actorID = actorDetailsArgs.selectedActorID
        actorViewModel.getActorDetail(actorID)
        actorViewModel.getActorCredits(actorID)
        actorViewModel.actorCredits.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    it.data?.let {
                        actorCreditsAdapter.differ.submitList(it.cast)
                    }
                }
                is Resource.Error ->
                    Log.i("TAG", "${it.message}")
                is Resource.Loading ->
                    Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
            }
        })
        actorViewModel.actorDetail.observe(viewLifecycleOwner,{
            when (it) {
                is Resource.Success -> {
                    it.data?.let {actor->
                        actorName.text=actor.name
                        actorBiography.text=actor.biography
                        Glide.with(actorPoster.context)
                                .load("https://image.tmdb.org/t/p/original/${actor.profilePath}")
                                .error(R.drawable.ic_baseline_looks_one_24)
                                .into(actorPoster)
                    }
                }
                is Resource.Error ->
                    Log.i("TAG", "${it.message}")
                is Resource.Loading ->
                    Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun loadView() {
        actorsRV.adapter = actorCreditsAdapter
        actorsRV.layoutManager = LinearLayoutManager(context)
    }
}