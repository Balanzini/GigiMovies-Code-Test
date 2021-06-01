package com.jose.gigimovies.ui.popular

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jose.gigimovies.R
import com.jose.gigimovies.domain.model.Movie
import com.jose.gigimovies.ui.loadImage
import kotlinx.android.synthetic.main.movie_item_layout.view.*

class MovieListAdapter: RecyclerView.Adapter<MovieHolder>() {

  private val movieList = ArrayList<Movie>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout, parent, false)
    return MovieHolder(view)
  }

  override fun getItemCount() = movieList.size

  override fun onBindViewHolder(holder: MovieHolder, position: Int) {
    val movie = movieList[position]
    holder.bind(movie)
  }

  fun setMovies(movies: List<Movie>){
    this.movieList.clear()
    this.movieList.addAll(movies)
    notifyDataSetChanged()
  }
}

class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun bind(movie: Movie) {
    itemView.iv_item_image.loadImage(movie.poster)
    itemView.tv_item_name.text = movie.title
  }
}