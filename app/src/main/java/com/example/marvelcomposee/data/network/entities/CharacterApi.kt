package com.example.marvelcomposee.data.network.entities

data class CharacterApi(
    val comics: Int,
    val description: String,
    val events: String,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>
)