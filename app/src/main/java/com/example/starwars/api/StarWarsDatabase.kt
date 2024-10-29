package com.example.starwars.api

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.starwars.api.dao.CustomTypeConverters
import com.example.starwars.api.dao.FilmsDao
import com.example.starwars.api.dao.PersonDao
import com.example.starwars.model.Film
import com.example.starwars.model.Person

@Database(entities = [Person::class, Film::class], version = 2)
@TypeConverters(CustomTypeConverters::class)
abstract class StarWarsDatabase : RoomDatabase() {
    abstract val peopleDao: PersonDao
    abstract val filmsDao: FilmsDao

    companion object {
        @Volatile
        private var INSTANCE: StarWarsDatabase? = null

        fun getInstance(context: Context): StarWarsDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    StarWarsDatabase::class.java,
                    "star_wars_database"
                ).fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}
