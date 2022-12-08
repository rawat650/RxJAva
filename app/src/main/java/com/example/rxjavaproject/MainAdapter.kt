package com.example.rxjavaproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rxjavaproject.databinding.LayoutRvItemBinding

class MainAdapter(val context: Context, var click:itemClickListner):RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    var movies = mutableListOf<Movie>()
    var mlistner = click
    fun setMovie(movies:List<Movie>){
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }
    fun deltedata(position: Int){
        movies.removeAt(position)
        notifyDataSetChanged()


    }
    class MainViewHolder(val binding: LayoutRvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutRvItemBinding.inflate(inflater,parent,false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val adapterPosition = holder.adapterPosition
val movie = movies[position]
holder.binding.movieTitle.setText(movie.title)

        holder.binding.movieYear.setText(movie.year)
        Glide.with(holder.itemView.context).load(movie.poster).into(holder.binding.moviePoster)
        holder.itemView.setOnClickListener {
            mlistner.onclick(position,movie)
        }
    }

    override fun getItemCount(): Int {
return movies.size
    }
    interface itemClickListner{
        fun onclick(position: Int,data:Movie)
    }

}