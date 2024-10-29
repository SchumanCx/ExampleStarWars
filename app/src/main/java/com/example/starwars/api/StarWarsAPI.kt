package com.example.starwars.api

import com.example.starwars.model.Film
import com.example.starwars.model.PageResponse
import com.example.starwars.model.Person
import retrofit2.http.GET

interface StarWarsService {

    @GET("people/")
    suspend fun getPeople(): PageResponse<List<Person>>

    @GET("films/")
    suspend fun getFilms(): PageResponse<List<Film>>

}
