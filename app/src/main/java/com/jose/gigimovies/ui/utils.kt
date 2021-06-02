package com.jose.gigimovies.ui

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(imagePath: String) {
  Glide
    .with(context)
    .load("https://image.tmdb.org/t/p/original$imagePath")
    .apply { centerCrop() }
    .into(this)
}