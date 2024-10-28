package com.example.starwars.model

data class ButtonData(
    val text: String,
    val onClick: () -> Unit
)
