package com.example.marvelcomposee.data.repositories

import com.example.marvelcomposee.data.entities.Event
import com.example.marvelcomposee.data.entities.Result
import com.example.marvelcomposee.data.network.ApiClient

object EventRepository : Repository<Event>() {
    suspend fun get() : Result<List<Event>> = super.get {
        ApiClient
            .eventsService
            .getEvents(0,100)
            .data
            .results
            .map { it.asEvent() }
    }
    suspend fun find(id: Int): Result<Event> = super.find(
        id = id,
        findActionRemote = {
            ApiClient
                .comicsService
                .findComic(id)
                .data
                .results
                .first()
                .asComic()
        }
    )
}


