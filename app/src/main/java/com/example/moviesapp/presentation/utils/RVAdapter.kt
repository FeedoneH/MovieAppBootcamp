package com.example.moviesapp.presentation.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


abstract class RVAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val callback = object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.toString() == newItem.toString()
        }


        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T):Boolean {
           return  oldItem == newItem
        }


    }
    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(viewType, parent, false)
        return getMyViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(differ.currentList[position])
    }

    override fun getItemViewType(position: Int): Int {

        return setLayout(position,  differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    abstract fun setLayout(position: Int, obj: T): Int
    abstract fun getMyViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder
    internal interface Binder<T> {
        fun bind(data: T)
    }
}