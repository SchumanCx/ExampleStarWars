@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.starwars.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.starwars.model.ButtonData
import com.example.starwars.ui.NavigationButton

@Composable
fun StarWarsScreen(navController: NavHostController) {
    val navigationList = listOf(
        ButtonData("People") {
            navController.navigate("people")
        },
        ButtonData("Planets", {}),
        ButtonData("Films", {}),
        ButtonData("Species", {}),
        ButtonData("Vehicles", {}),
        ButtonData("Starships", {})
    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Star Wars API")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(
                vertical = innerPadding.calculateTopPadding() + 16.dp,
                horizontal = 16.dp,
            )
        ) {
            items(navigationList) { data ->
                NavigationButton(
                    text = data.text,
                    onClick = data.onClick
                )
            }
        }
    }
}