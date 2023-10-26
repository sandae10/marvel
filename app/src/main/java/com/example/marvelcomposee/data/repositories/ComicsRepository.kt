package com.example.marvelcomposee.data.repositories

import com.example.marvelcomposee.data.entities.Comic
import com.example.marvelcomposee.data.entities.Result
import com.example.marvelcomposee.data.entities.tryCall
import com.example.marvelcomposee.data.network.ApiClient

object ComicsRepository {
    suspend fun get(format: Comic.Format? = null) : Result<List<Comic>> = tryCall {
         ApiClient
            .comicsService
            .getComics(0, 20, format?.toStringFormat())
            .data
            .results
            .map { it.asComic() }
    }

    suspend fun find(id: Int): Result<Comic> = tryCall {
         ApiClient
            .comicsService
            .findComic(id)
            .data
            .results
            .first()
            .asComic()
    }
}
