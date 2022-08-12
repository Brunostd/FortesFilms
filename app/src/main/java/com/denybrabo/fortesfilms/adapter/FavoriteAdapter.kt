package com.denybrabo.fortesfilms.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.denybrabo.fortesfilms.databinding.AllMoviesBinding
import com.denybrabo.fortesfilms.models.MoviesSagaModel
import com.denybrabo.fortesfilms.ui.HomeFragmentDirections
import com.denybrabo.fortesfilms.ui.MoviesFavoriteFragmentDirections

class FavoriteAdapter(var listMovies: MutableList<MoviesSagaModel>): RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {
    class MyViewHolder(private val itemBinding: AllMoviesBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(data: MoviesSagaModel){
            itemBinding.run {
                this.titleMovie.text        = data.title
                var url                     = data.poster
                this.dateLaunchMovie.text   = data.released
                this.genreMovie.text        = data.genre

                Glide.with(itemView.context).load(url).into(this.imageMovie)

                this.cardMovie.setOnClickListener {
                    val action = MoviesFavoriteFragmentDirections.actionMoviesFavoriteFragmentToMovieDetailsFragment(data, true)
                    this.root.findNavController().navigate(action)
                }
            }
        }
        companion object{
            fun create(parent: ViewGroup): MyViewHolder{
                val itemBinding = AllMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MyViewHolder(itemBinding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int = listMovies.size
}