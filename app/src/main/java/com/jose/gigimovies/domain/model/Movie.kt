package com.jose.gigimovies.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val poster: String,
    val rating: Float,
    var favourite: Boolean
)