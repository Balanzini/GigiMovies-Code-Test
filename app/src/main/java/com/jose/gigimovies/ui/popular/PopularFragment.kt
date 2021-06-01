package com.jose.gigimovies.ui.popular

import android.animation.LayoutTransition
import android.app.SearchManager
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
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

    setHasOptionsMenu(true)
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

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.movie_search_menu, menu)
    val mSearchView = menu.findItem(R.id.action_search)?.actionView as SearchView

    mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {
        popularViewModel.getMovies(query ?: "")
        return true
      }

      override fun onQueryTextChange(newText: String?): Boolean {
        return true
      }

    })

    val searchBar = mSearchView.findViewById(R.id.search_bar) as LinearLayout
    searchBar.layoutTransition = LayoutTransition()

  }


}