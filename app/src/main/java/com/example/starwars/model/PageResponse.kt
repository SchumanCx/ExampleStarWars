package com.example.starwars.model

data class PageResponse<Data>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: Data
)
