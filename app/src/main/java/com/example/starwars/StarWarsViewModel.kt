package com.example.starwars

import androidx.lifecycle.ViewModel
import com.example.starwars.api.DataResult
import com.example.starwars.api.StarWarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class StarWarsViewModel @Inject constructor(private val repository: StarWarsRepository) :
    ViewModel() {

    val rootData: Flow<DataResult<Map<String, String>>> = repository.getRootData()

    fun getNextPageData(url: String): Flow<DataResult<Map<String, String>>> =
        repository.getNextPageData(url)
}
