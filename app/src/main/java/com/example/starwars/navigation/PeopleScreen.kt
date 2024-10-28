@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.starwars.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.starwars.StarWarsViewModel
import com.example.starwars.api.DataResult
import com.example.starwars.model.Person

@Composable
fun PeopleScreen(viewModel: StarWarsViewModel, navController: NavController) {
    var result by remember { mutableStateOf<DataResult<List<Person>>>(DataResult.Loading) }

    LaunchedEffect(Unit) {
        viewModel.getPeople().collect { peopleList ->
            result = peopleList
        }
    }

    when (result) {
        is DataResult.Loading -> Text(text = "Loading...")

        is DataResult.Success -> {
            val people = (result as DataResult.Success).data

            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text("Star Wars People")
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        navigationIcon = {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Localized description"
                                )
                            }
                        }
                    )
                }
            ) { innerPadding ->
                LazyColumn(
                    modifier = Modifier.padding(
                        vertical = innerPadding.calculateTopPadding() + 16.dp,
                        horizontal = 16.dp,
                    )
                ) {
                    items(people) { person ->
                        Text(text = person.name)
                    }
                }
            }
        }

        is DataResult.Error -> Text(text = "Error")
    }
}
