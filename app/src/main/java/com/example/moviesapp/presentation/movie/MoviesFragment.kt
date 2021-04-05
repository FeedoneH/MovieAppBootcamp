package com.example.moviesapp.presentation.movie

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.MainActivity
import com.example.moviesapp.R
import com.example.moviesapp.data.model.movie.Movie
import com.example.moviesapp.data.util.Resource.Success
import com.example.moviesapp.databinding.ListItemBinding
import com.example.moviesapp.databinding.HorizontalListItemBinding
import com.example.moviesapp.presentation.utils.RVAdapter
import com.example.moviesapp.data.util.Resource.*
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.horizontal_list_item.view.*

class MoviesFragment : Fragment() {


    lateinit var binding: ListItemBinding;
    lateinit var viewModel: MovieViewModel;


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ListItemBinding.inflate(layoutInflater)
        viewModel = (activity as MainActivity).viewmodel
        getList()
        showRV()


    }


    class MyHorizontalViewHolder(itemView: HorizontalListItemBinding, viewType: Int) : RecyclerView.ViewHolder(itemView.root),
            RVAdapter.Binder<Movie> {

        override fun bind(data: Movie) {

            itemView.setOnClickListener {
                var action = MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(selectedMovieID = data.id.toString(), null)
                findNavController(it).navigate(action)
            }
            itemView.titletext.text = data.title
            itemView.votetext.text = data.voteAverage.toString()
            Glide.with(itemView.posterImg.context)
                    .load("https://image.tmdb.org/t/p/w185/${data.posterPath}")
                    .error(R.drawable.ic_baseline_looks_one_24)
                    .into(itemView.posterImg)
        }

    }

    class MyViewHolder(itemView: ListItemBinding, viewType: Int) : RecyclerView.ViewHolder(itemView.root),
            RVAdapter.Binder<Movie> {

        override fun bind(movie: Movie) {

            itemView.setOnClickListener {
                var action = MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(selectedMovieID = movie.id.toString(), null)
                findNavController(it).navigate(action)
            }

            itemView.titleTV.text = movie.title
            itemView.mediaTypeText.visibility = TextView.INVISIBLE
            itemView.voteTV?.text = movie.voteAverage.toString()
            Glide.with(itemView.imageView.context)
                    .load("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
                    .error(R.drawable.ic_baseline_close_24)
                    .into(itemView.imageView)
        }

    }

    val nowPlayingAdapter = object : RVAdapter<Movie>() {
        override fun setLayout(position: Int, obj: Movie): Int {

            return R.layout.horizontal_list_item

        }

        override fun getMyViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val itemView = HorizontalListItemBinding.inflate(inflater)
            return MyHorizontalViewHolder(itemView, viewType)
        }
    }

    val popularAdapter = object : RVAdapter<Movie>() {
        override fun setLayout(position: Int, obj: Movie): Int {
            return R.layout.list_item
        }

        override fun getMyViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val listItemView = ListItemBinding.inflate(inflater)
            return MyViewHolder(listItemView, viewType)
        }
    }


    private fun showRV() {

        nowPlayingRV.adapter = nowPlayingAdapter
        nowPlayingRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        popularRV.adapter = popularAdapter
        popularRV.layoutManager = LinearLayoutManager(context)
    }

    private fun getList() {
        viewModel.getnowPlaying()
        viewModel.getMoviesList()
        viewModel.nowPlayingMoviesList.observe(viewLifecycleOwner, {
            when (it) {
                is Success -> {
                    it.data?.let {
                        var movieList = it.results
                        nowPlayingAdapter.differ.submitList(movieList)

                    }
                }
                is Error ->
                    Log.i("TAG", "${it.message}")
                is Loading ->
                    Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.popularMoviesList.observe(viewLifecycleOwner, {

            when (it) {
                is Success -> {
                    it.data?.let {
                        popularAdapter.differ.submitList(it.results)
                    }
                }
                is Error ->
                    Log.i("TAG", "${it.message}")
                is Loading ->
                    Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
            }
        })

    }

}