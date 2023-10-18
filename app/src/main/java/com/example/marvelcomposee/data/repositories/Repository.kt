package com.example.marvelcomposee.data.repositories

import com.example.marvelcomposee.data.entities.MarvelItem

abstract class Repository<T : MarvelItem> {
    private var cache : List<T> = emptyList()

    internal suspend fun get(getAction: suspend () -> List<T>) : List<T> {
        if (cache.isEmpty()) {
            cache = getAction()
        }
        return cache
    }

    internal suspend fun find(
        id: Int,
        findActionRemote: suspend () -> T
    ): T {
        val character = cache.find { it.id == id }
        if (character !== null) return character

        return findActionRemote()
    }
}
