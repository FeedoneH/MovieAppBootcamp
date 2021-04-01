package com.example.moviesapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.data.model.tvshow.TvShow
import com.example.moviesapp.data.util.Resource
import com.example.moviesapp.databinding.FragmentTvShowsBinding
import com.example.moviesapp.databinding.HorizontalListItemBinding
import com.example.moviesapp.databinding.ListItemBinding
import com.example.moviesapp.presentation.tvshow.TvShowViewModel
import com.example.moviesapp.presentation.utils.RVAdapter
import kotlinx.android.synthetic.main.fragment_tv_shows.*
import kotlinx.android.synthetic.main.horizontal_list_item.view.*
import kotlinx.android.synthetic.main.list_item.view.*

class TvShowsFragment : Fragment() {

    lateinit var binding: FragmentTvShowsBinding
    lateinit var viewModel: TvShowViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_shows, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTvShowsBinding.inflate(layoutInflater)
        viewModel = (activity as MainActivity).tvShowViewModel
        Log.i("TAG", "onViewCreated:${viewModel.populartvShowList.value?.data?.results} ")
        showRV()
        getList()


    }


    class MyViewHolder(itemView: HorizontalListItemBinding, viewType: Int) : RecyclerView.ViewHolder(itemView.root),
            RVAdapter.Binder<TvShow> {
        override fun bind(tvShow: TvShow) {
            itemView.titletext.text = tvShow.name
            itemView.votetext.text = tvShow.voteAverage.toString()
            Glide.with(itemView.posterImg.context)
                    .load("https://image.tmdb.org/t/p/w185/${tvShow.posterPath}")
                    .error(R.drawable.ic_baseline_looks_one_24)
                    .into(itemView.posterImg)

            itemView.setOnClickListener {
                val action = TvShowsFragmentDirections.actionTvShowsFragmentToDetailsFragment(null, tvShow.id.toString())
                findNavController(it).navigate(action)
            }
        }
    }

    class GridItemViewHolder(itemView: HorizontalListItemBinding, viewType: Int) : RecyclerView.ViewHolder(itemView.root),
            RVAdapter.Binder<TvShow> {
        override fun bind(tvShow: TvShow) {
            itemView.titletext.text = tvShow.name
            itemView.votetext.text = tvShow.voteAverage.toString()
            itemView.setOnClickListener {
                val action = TvShowsFragmentDirections.actionTvShowsFragmentToDetailsFragment(null, tvShow.id.toString())
                findNavController(it).navigate(action)
            }
            itemView.titletext.layoutParams.width = 300
            Glide.with(itemView.posterImg.context)
                    .load("https://image.tmdb.org/t/p/w185/${tvShow.posterPath}")
                    .error(R.drawable.ic_baseline_looks_one_24)
                    .into(itemView.posterImg)
        }

    }

    var popularTvShowAdapter = object : RVAdapter<TvShow>() {
        override fun getLayoutId(position: Int, obj: TvShow): Int {
            return R.layout.horizontal_list_item
        }

        override fun getMyViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val listItemView = HorizontalListItemBinding.inflate(inflater)
            return MyViewHolder(listItemView, viewType)
        }

    }

    var topRatedTvShowAdapter = object : RVAdapter<TvShow>() {
        override fun getLayoutId(position: Int, obj: TvShow): Int {
            return R.layout.horizontal_list_item
        }

        override fun getMyViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val listItemView = HorizontalListItemBinding.inflate(inflater)
            return GridItemViewHolder(listItemView, viewType)
        }

    }

    fun showRV() {
        popularTvShowRV.adapter = popularTvShowAdapter
        popularTvShowRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        topRatedTvRV.adapter = topRatedTvShowAdapter
        topRatedTvRV.layoutManager = GridLayoutManager(context, 2)
    }

    fun getList() {
        viewModel.getPopularTvShows()
        viewModel.getTopRatedTvShows()
        viewModel.populartvShowList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    it.data?.let {
                        popularTvShowAdapter.differ.submitList(it.results)
                        Log.i("TAGLIST", "${it.results}")
                        Log.i("TAGLIST", "${popularTvShowAdapter.differ.currentList}")

                    }
                }
                is Resource.Error ->
                    Log.i("TAG", "${it.message}")
                is Resource.Loading ->
                    Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.topRatedTvShowList.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Success -> {
                    it.data?.let {
                        topRatedTvShowAdapter.differ.submitList(it.results)
                        Log.i("TAGlist", "${it.results}")
                    }
                }
                is Resource.Error ->
                    Log.i("TAG", "${it.message}")
                is Resource.Loading ->
                    Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
            }
        })

    }

}