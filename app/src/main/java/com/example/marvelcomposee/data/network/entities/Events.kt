package com.example.marvelcomposee.data.network.entities

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Serie>,
    val returned: Int
)