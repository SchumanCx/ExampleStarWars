package com.example.starwars.ui

import androidx.lifecycle.ViewModel
import com.example.starwars.api.DataResult
import com.example.starwars.api.StarWarsRepository
import com.example.starwars.model.Film
import com.example.starwars.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class StarWarsViewModel @Inject constructor(private val repository: StarWarsRepository) :
    ViewModel() {

    fun getPeople(): Flow<DataResult<List<Person>>> = repository.getPeople()
    fun getFilms(): Flow<DataResult<List<Film>>> = repository.getFilms()
}
