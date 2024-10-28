package com.example.starwars.api

import com.example.starwars.model.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException
import javax.inject.Inject

class StarWarsRepository @Inject constructor(
    private val apiService: StarWarsService,
    private val db: StarWarsDatabase
) {

    fun getPeople(): Flow<DataResult<List<Person>>> {
        return flow {
            emit(DataResult.Loading)

            // Check if data is available locally
            val localPeople = db.peopleDao.getAllPeople()
            if (localPeople.isNotEmpty()) {
                emit(DataResult.Success(localPeople))
            }
            // Fetch data from API and store it locally
            val remotePeople = apiService.getPeople().results
            db.peopleDao.insertPeople(remotePeople)
            emit(DataResult.Success(remotePeople))
        }.catch {

            emit(
                DataResult.Error(
                    db.peopleDao.getAllPeople(),
                    if ((it is UnknownHostException).not()) {
                        it
                    } else {
                        Exception("No internet connection")
                    }
                )
            )
        }
    }
}
