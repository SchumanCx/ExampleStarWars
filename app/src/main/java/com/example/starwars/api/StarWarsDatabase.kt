package com.example.starwars.api

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.starwars.api.dao.PersonDao
import com.example.starwars.model.Person

@Database(entities = [Person::class], version = 1)
abstract class StarWarsDatabase : RoomDatabase() {
    abstract val peopleDao: PersonDao

    companion object {
        @Volatile
        private var INSTANCE: StarWarsDatabase? = null

        fun getInstance(context: Context): StarWarsDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    StarWarsDatabase::class.java,
                    "star_wars_database"
                ).build()
            }
        }
    }
}
