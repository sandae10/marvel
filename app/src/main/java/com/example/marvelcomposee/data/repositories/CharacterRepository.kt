package com.example.marvelcomposee.data.repositories

import com.example.marvelcomposee.data.network.ApiClient
import com.example.marvelcomposee.data.entities.Character
import com.example.marvelcomposee.data.network.entities.CharacterApi as NetworkCharacter

import com.example.marvelcomposee.data.network.entities.asString

object CharacterRepository {

    suspend fun getCharacters(): List<Character> {
        val result = ApiClient.charactersService.getCharacter(0,100)

        return result.data.results.map  { it.asCharacter() }
    }

    suspend fun findCharacter(characterId : Int): Character {
        val result = ApiClient.charactersService.findCharacter(characterId)
        return result.data.results.first().asCharacter()
    }
}

fun NetworkCharacter.asCharacter() =
    Character(id, name, description, thumbnail.asString(), emptyList(), emptyList(), emptyList(), emptyList())