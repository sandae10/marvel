package com.example.marvelcomposee.data.network.entities

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Serie>,
    val returned: Int
)