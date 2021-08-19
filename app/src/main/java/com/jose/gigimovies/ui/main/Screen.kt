package com.jose.gigimovies.ui.main

import androidx.annotation.DrawableRes
import com.jose.gigimovies.R

sealed class Screen(val route: String, val title : String, @DrawableRes val icon : Int){
  object Popular: Screen("popular", "Popular", R.drawable.ic_movie_24)
  object Favourite: Screen("favourite", "Favourite", R.drawable.ic_favorite_24)
}
