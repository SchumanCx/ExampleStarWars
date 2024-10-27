package com.example.starwars.api

import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsService {

    @GET("/")
    suspend fun getRoot(): Map<String, String>

    @GET("{path}")
    suspend fun getNextPage(@Path("path") url: String): Map<String, String>

}
