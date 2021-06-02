package com.jose.gigimovies.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val poster: String,
    val release: String,
    val favourite: Boolean
)