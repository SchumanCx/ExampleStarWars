@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.starwars.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.starwars.R
import com.example.starwars.api.DataResult
import com.example.starwars.model.Person
import com.example.starwars.ui.CharacterListItem
import com.example.starwars.ui.StarWarsViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class PeopleScreen(
    val personIds: List<Int>
)

@Composable
fun PeopleScreen(
    viewModel: StarWarsViewModel,
    navController: NavController,
    personIds: List<Int>
) {
    var result by remember { mutableStateOf<DataResult<List<Person>>>(DataResult.Loading) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        if (personIds.isEmpty()) {
            viewModel.getPeople().collect { peopleList ->
                result = peopleList
            }
        } else {
            //todo: request specific people
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(stringResource(R.string.people_screen_heading))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.navigate_back_content_description)
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        when (result) {
            is DataResult.Loading -> Text(text = stringResource(R.string.loading))

            is DataResult.Success -> {
                val people = (result as DataResult.Success).data

                List(innerPadding, navController, people)
            }

            is DataResult.Error<*> -> {
                val people = (result as DataResult.Error<List<Person>>).data
                val error = (result as DataResult.Error<List<Person>>).exception.message
                val apiErrorMessage = stringResource(R.string.api_call_failed)

                LaunchedEffect(result) {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = error ?: apiErrorMessage,
                            duration = SnackbarDuration.Short
                        )
                    }
                }

                List(innerPadding, navController, people)
            }
        }
    }
}

@Composable
private fun List(
    innerPadding: PaddingValues,
    navController: NavController,
    people: List<Person>
) {
    LazyColumn(
        modifier = Modifier.padding(
            top = innerPadding.calculateTopPadding() + 16.dp,
            start = 16.dp,
            end = 16.dp
        )
    ) {
        items(people) { person ->
            CharacterListItem(
                person = person,
                navController = navController,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}
