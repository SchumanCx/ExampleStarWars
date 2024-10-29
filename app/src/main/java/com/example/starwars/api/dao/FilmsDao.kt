package com.example.starwars.api.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwars.model.Film

@Dao
interface FilmsDao {
    @Query("SELECT * FROM film")
    suspend fun getAllFilms(): List<Film>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(films: List<Film>)

    @Query("DELETE FROM film")
    suspend fun deleteAllFilms()
}
