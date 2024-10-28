package com.example.starwars.model

data class PersonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Person>
)
