package com.example.starwars.api.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwars.model.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    suspend fun getAllPeople(): List<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(people: List<Person>)

    @Query("DELETE FROM person")
    suspend fun deleteAllPeople()
}
