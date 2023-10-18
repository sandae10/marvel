package com.example.marvelcomposee.data.repositories

import com.example.marvelcomposee.data.entities.Character
import com.example.marvelcomposee.data.entities.Comic
import com.example.marvelcomposee.data.entities.Event
import com.example.marvelcomposee.data.entities.Reference

fun ApiCharacter.asCharacter() : Character = Character(
    id,
    name,
    description,
    thumbnail.asString(),
    listOf(
        comics.toDomain(References.Type.COMIC),
        events.toDomain(References.Type.EVENT),
        series.toDomain(References.Type.SERIES),
        stories.toDomain(References.Type.STORY),
    ),
    urls.map {Url(it.type, it.url)}
)

fun ApiEvent.asEvent():Event = Event(
    id,
    title,
    description,
    thumbnail.asString(),
    listOf(
        comics.toDomain(References.Type.COMIC),
        characters.toDomain(References.Type.CHARACTERS),
        series.toDomain(References.Type.SERIES),
        stories.toDomain(References.Type.STORY),
    ),
    urls.map {Url(it.type, it.url)}
)

fun ApiComic.asComic(): Comic = Comic(
    id,
    title,
    description ?: "" ,
    thumbnail.asString(),
    format.toDomain(),
    listOf(
        characters.toDomain(References.Type.CHARACTERS),
        events.toDomain(References.Type.EVENT),
        series.toDomain(References.Type.SERIES),
        stories.toDomain(References.Type.STORY),
        ),
    urls.map {Url(it.type, it.url)}
)

private fun String.toDomain(): Comic.Format = when (this) {
    "magazine" -> Comic.Format.MAGAZINE
    "trade paperback" -> Comic.Format.TRADE_PAPERBACK
    "hardcover" -> Comic.Format.HARDCOVER
    "digest" -> Comic.Format.DIGEST
    "graphic novel" -> Comic.Format.GRAPHIC_NOVEL
    "digital comic" -> Comic.Format.DIGITAL_COMIC
    "infinite comic" -> Comic.Format.INFINITE_COMIC
    else -> Comic.Format.COMIC
}
fun Comic.Format.toStringFormat(): String = when(this) {
    Comic.Format.COMIC -> "comic"
    Comic.Format.MAGAZINE -> "magazine"
    Comic.Format.TRADE_PAPERBACK -> "trade paperback"
    Comic.Format.HARDCOVER -> "hardcover"
    Comic.Format.DIGEST -> "digest"
    Comic.Format.GRAPHIC_NOVEL -> "graphic novel"
    Comic.Format.DIGITAL_COMIC -> "digital comic"
    Comic.Format.INFINITE_COMIC -> "infinite comic"
}

private fun  ApiReference.toDomain(type: Reference.Type).Reference {
    return Reference(
        type,
    )
}