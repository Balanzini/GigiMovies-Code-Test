package com.jose.gigimovies.ui.favourites

import androidx.recyclerview.widget.DiffUtil
import com.jose.gigimovies.domain.model.Movie

class MovieDiff(private val newList: List<Movie>, private val oldList: List<Movie>) :
  DiffUtil.Callback() {

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldList[oldItemPosition].id == newList[newItemPosition].id
  }

  override fun getOldListSize() = oldList.size

  override fun getNewListSize() = newList.size

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    val old = oldList[oldItemPosition]
    val new = newList[newItemPosition]
    return old.title == new.title && old.poster == new.poster
  }
}