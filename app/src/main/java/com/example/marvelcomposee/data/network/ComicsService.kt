package com.example.marvelcomposee.data.network

import com.example.marvelcomposee.data.network.entities.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicsService {
    @GET("/v1/public/comics")
    suspend fun getComics(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("format") format : String?
    ): ApiResponse<ApiComic>

    @GET("/v1/public/comics/{comicsId}")
    suspend fun findComic(
        @Path("comicId") comicsId: Int
    ): ApiResponse<ApiComic>
}