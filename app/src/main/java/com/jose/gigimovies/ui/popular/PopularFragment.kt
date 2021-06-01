package com.jose.gigimovies.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.jose.gigimovies.R
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_movies.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class PopularFragment : Fragment() {

  private val popularViewModel: PopularViewModel by viewModel()
  private val adapter = MovieListAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    popularViewModel.movieList.observe(this, Observer {
      adapter.setMovies(it)
      srl_refresh.isRefreshing = false
    })
  }
  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {


    val root = inflater.inflate(R.layout.fragment_movies, container, false)

    root.rv_movies.adapter = adapter
    root.srl_refresh.isRefreshing = true
    root.srl_refresh.setOnRefreshListener {
      popularViewModel.getMovies()
    }

    popularViewModel.getMovies()

    return root
  }

}