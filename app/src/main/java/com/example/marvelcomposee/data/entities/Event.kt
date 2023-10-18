package com.example.marvelcomposee.data.entities

import com.example.marvelcomposee.data.network.entities.Url

data class Event(
    override val id: Int,
    override val title: String,
    override val description: String,
    override val references : List<Reference>,
    override val urls: List<Url>, override val thumbnail: String
) : MarvelItem