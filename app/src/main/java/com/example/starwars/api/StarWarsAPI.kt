package com.example.starwars.api

import com.example.starwars.model.PersonResponse
import retrofit2.http.GET

interface StarWarsService {

    @GET("people/")
    suspend fun getPeople(): PersonResponse

}
