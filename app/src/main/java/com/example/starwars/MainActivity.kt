package com.example.starwars

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.starwars.navigation.FilmScreen
import com.example.starwars.navigation.PeopleScreen
import com.example.starwars.navigation.StarWarsScreen
import com.example.starwars.ui.StarWarsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<StarWarsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "root") {
                composable(route = "root") {
                    StarWarsScreen(navController = navController)
                }
                composable(
                    route = "people?{personIds}",
                    arguments = listOf(navArgument("personIds") {
                        type = NavType.IntListType
                        defaultValue = emptyList<Int>()
                    })
                ) {
                    PeopleScreen(
                        viewModel = viewModel,
                        navController = navController,
                        personIds = it.arguments?.getIntegerArrayList("personIds") ?: emptyList()
                    )
                }
                composable(
                    route = "films?{filmIds}",
                    arguments = listOf(navArgument("filmIds") {
                        type = NavType.IntListType
                        defaultValue = emptyList<Int>()
                    })
                ) {
                    FilmScreen(
                        viewModel = viewModel,
                        navController = navController,
                        filmIds = it.arguments?.getIntegerArrayList("filmIds") ?: emptyList()
                    )
                }
                composable(route = "planets") {
                    // PlanetsScreen()
                }
                composable(route = "species") {
                    // SpeciesScreen()
                }
                composable(route = "vehicles") {
                    // VehiclesScreen()
                }
                composable(route = "starships") {
                    // StarshipsScreen()
                }
            }
        }
    }
}
