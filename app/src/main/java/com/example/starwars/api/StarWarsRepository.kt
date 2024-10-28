package com.example.starwars.api

import com.example.starwars.model.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StarWarsRepository @Inject constructor(private val apiService: StarWarsService) {

    fun getPeople(): Flow<DataResult<List<Person>>> {
        return flow {
            emit(DataResult.Loading)
            emit(DataResult.Success(apiService.getPeople().results))
        }.catch {
            emit(DataResult.Error(it))
        }
    }
}
