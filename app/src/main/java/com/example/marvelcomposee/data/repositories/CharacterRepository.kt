package com.example.marvelcomposee.data.repositories

import com.example.marvelcomposee.data.network.ApiClient
import com.example.marvelcomposee.data.entities.Character
import com.example.marvelcomposee.data.entities.Reference
import com.example.marvelcomposee.data.entities.Result
import com.example.marvelcomposee.data.network.entities.CharacterApi as NetworkCharacter
import com.example.marvelcomposee.data.network.entities.asString

object CharacterRepository : Repository<Character>() {
    suspend fun get() : Result<List<Character>> = super.get {
        ApiClient
            .charactersService
            .getCharacter(0,100)
            .data
            .results
            .map { it.asCharacter() }
    }
    suspend fun find(id: Int): Result<Character> = super.find(
        id,
        findActionRemote = {
                ApiClient
                    .charactersService
                    .findCharacter(id)
                    .data
                    .results
                    .first()
                    .asCharacter()
        }
    )
}

fun NetworkCharacter.asCharacter(): Character {
    val comics = comics.items.map {Reference(it.name)}
    val series = series.items.map {Reference(it.name)}
    val events = events.items.map {Reference(it.name)}
    val stories = stories.items.map {Reference(it.name)}

    return Character(
        id,
        name,
        description,
        thumbnail.asString(),
        comics,
        series,
        events,
        stories,
        urls
    )
}