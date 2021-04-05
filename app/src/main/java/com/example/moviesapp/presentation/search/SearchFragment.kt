package com.example.moviesapp.presentation.search

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.MainActivity
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentSearchBinding
import com.example.moviesapp.databinding.ListItemBinding
import com.example.moviesapp.presentation.utils.RVAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.coroutines.MainScope

import kotlinx.coroutines.launch
import com.example.moviesapp.data.model.search.Result


class SearchFragment : Fragment() {
    lateinit var searchViewModel: SearchViewModel
    lateinit var binding: FragmentSearchBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        searchViewModel = (activity as MainActivity).searchViewModel
        searchView.onActionViewExpanded()
        getSearchList()
        setSearchView()
        loadRV()


    }

    fun setSearchView() {
        searchView.setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(text: String?): Boolean {
                        if (text!!.length >= 3) {
                            searchViewModel.getSearchList(text.toString())
                            getSearchList()
                            loadRV()
                            return false
                        }
                        return false
                    }

                    override fun onQueryTextChange(text: String?): Boolean {
                        MainScope().launch {

                            if (text!!.length >= 3) {
                                searchViewModel.getSearchList(text.toString())
                                getSearchList()
                                loadRV()
                            }
                        }
                        return false
                    }

                }
        )
        searchView.setOnCloseListener {
            getSearchList()
            loadRV()
            false
        }
    }

    fun getSearchList() {
        searchViewModel.searchList.observe(viewLifecycleOwner, { response ->
            when (response) {
                is com.example.moviesapp.data.util.Resource.Success -> {


                    response.data?.let {
                        Log.i("MYTAG", "came here ${it.results.toList().size}")
                        searchRVAdapter.differ.submitList(it.results)
                    }
                }
                is com.example.moviesapp.data.util.Resource.Error -> {

                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                                .show()
                    }
                }

                is com.example.moviesapp.data.util.Resource.Loading -> {

                    Toast.makeText(activity, "Loading ", Toast.LENGTH_LONG)
                            .show()

                }

            }
        })
    }

    class MyViewHolder(itemView: ListItemBinding, viewType: Int) : RecyclerView.ViewHolder(itemView.root),
            RVAdapter.Binder<Result> {

        override fun bind(result: Result) {

            itemView.setOnClickListener {
                when (result.mediaType) {
                    "movie" -> {
                        val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(result.id.toString(), null)
                        Navigation.findNavController(it).navigate(action)
                    }
                    "tv" -> {
                        val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(null, result.id.toString())
                        Navigation.findNavController(it).navigate(action)
                    }
                    "person" -> {
                        val action = SearchFragmentDirections.actionSearchFragmentToActorDetailsFragment(result.id.toString())
                        Navigation.findNavController(it).navigate(action)
                    }
                }

            }

            itemView.titleTV.text = if (result.mediaType == "movie") result.title else result.name
            itemView.mediaTypeText.text = result.mediaType.capitalize()
            itemView.voteTV?.text = result.voteAverage.toString()
            Glide.with(itemView.imageView.context)
                    .load("https://image.tmdb.org/t/p/w185/${result.posterPath}")
                    .error(R.drawable.ic_baseline_looks_one_24)
                    .into(itemView.imageView)
            when (result.mediaType) {
                "movie" -> {
                    itemView.mediaTypeText.setTextColor(Color.parseColor("#3333cc"))
                }
                "tv" -> {
                    itemView.mediaTypeText.setTextColor(Color.parseColor("#e0e006"))
                }
                "person" -> {
                    itemView.mediaTypeText.setTextColor(Color.parseColor("#00cc00"))
                }
            }
        }

    }

    val searchRVAdapter = object : RVAdapter<Result>() {

        override fun setLayout(position: Int, obj: Result): Int {
            return R.layout.list_item
        }

        override fun getMyViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val listItemView = ListItemBinding.inflate(inflater)
            return MyViewHolder(listItemView, viewType)
        }
    }

    fun loadRV() {
        searchRV.adapter = searchRVAdapter

        searchRV.layoutManager = LinearLayoutManager(context)
    }
}


