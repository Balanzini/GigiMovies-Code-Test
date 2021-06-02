package com.jose.gigimovies.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.jose.gigimovies.R
import kotlinx.android.synthetic.main.fragment_movies.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavouritesFragment : Fragment() {

  private val favouritesViewModel: FavouritesViewModel by viewModel()
  private val adapter = FavouriteListAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    favouritesViewModel.movieList.observe(this, Observer {
      adapter.setMovies(it)
    })

    adapter.onDelete = {index ->
      favouritesViewModel.deleteFavourite(index)
    }

  }
  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_favourites, container, false)
    root.rv_movies.adapter = adapter

    return root
  }
}