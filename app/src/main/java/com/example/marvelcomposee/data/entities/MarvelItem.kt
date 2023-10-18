package com.example.marvelcomposee.data.entities

import com.example.marvelcomposee.data.network.entities.Url

interface MarvelItem {
    val id: Int
    val title: String
    val description: String
    val thumbnail: String
    val references: List<Reference>
    val urls: List<Url>
}