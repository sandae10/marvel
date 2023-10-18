package com.example.marvelcomposee.data.entities

import com.example.marvelcomposee.data.network.entities.Url

data class Comic(
    override val id: Int,
    override val title: String,
    override val description: String,
    override val thumbnail: String,
    val format : Format,
    override val references : List<Reference>,
    override val urls: List<Url>
) : MarvelItem {
    enum class Format  {
        COMIC,
        MAGAZINE,
        TRADE_PAPERBACK,
        HARDCOVER,
        DIGEST,
        GRAPHIC_NOVEL,
        DIGITAL_COMIC,
        INFINITE_COMIC
    }
}
