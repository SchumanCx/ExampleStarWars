package com.example.starwars.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StarWarsRepository @Inject constructor(private val apiService: StarWarsService) {

    fun getRootData(): Flow<DataResult<Map<String, String>>> {
        return flow {
            emit(DataResult.Loading)
            emit(DataResult.Success(apiService.getRoot()))
        }.catch {
            emit(DataResult.Error(it))
        }
    }

    fun getNextPageData(url: String): Flow<DataResult<Map<String, String>>> {
        return flow {
            emit(DataResult.Loading)
            emit(DataResult.Success(apiService.getNextPage(url)))
        }.catch {
            emit(DataResult.Error(it))
        }
    }
}
