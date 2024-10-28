package com.example.starwars.api.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CustomTypeConverters {
    @TypeConverter
    fun fromStringList(value: List<String>): String = value.toJson()

    @TypeConverter
    fun toStringList(value: String): List<String> = value.fromJson()
}

inline fun <reified T> T.toJson(): String =
    Gson().toJson(this, object : TypeToken<T>() { }.type)

inline fun <reified T> String.fromJson(): T =
    Gson().fromJson(this, object : TypeToken<T>() { }.type)

