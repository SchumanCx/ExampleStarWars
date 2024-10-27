package com.example.starwars

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.starwars.api.DataResult

@Composable
fun StarWarsScreen(viewModel: StarWarsViewModel) {
    val rootData by viewModel.rootData.collectAsState(initial = DataResult.Loading)

    when (rootData) {
        is DataResult.Loading -> {
            // Show loading indicator
        }

        is DataResult.Success -> {
            val data = (rootData as DataResult.Success<Map<String, String>>).data
            // Use the data to populate the UI
        }

        is DataResult.Error -> {
            // Handle error
        }
    }
}
