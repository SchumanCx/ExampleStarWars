package com.example.starwars.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Person(
    @PrimaryKey
    val name: String,
    val height: Int,
    val mass: Int,
    @SerializedName("hair_color")
    val hairColor: String,
    @SerializedName("skin_color")
    val skinColor: String,
    @SerializedName("eye_color")
    val eyeColor: String,
    @SerializedName("birth_year")
    val birthYear: String,
    val gender: String,
    val films: List<String>,
    val species: List<String>,
    val vehicles: List<String>,
    val starships: List<String>
)
