package com.example.starwars

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
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
            NavHost(navController = navController, startDestination = StarWarsScreen) {
                composable<StarWarsScreen> {
                    StarWarsScreen(navController = navController)
                }
                composable<PeopleScreen> {
                    val args = it.toRoute<PeopleScreen>()
                    PeopleScreen(
                        viewModel = viewModel,
                        navController = navController,
                        personIds = args.personIds
                    )
                }
                composable<FilmScreen> {
                    val args = it.toRoute<FilmScreen>()
                    FilmScreen(
                        viewModel = viewModel,
                        navController = navController,
                        filmIds = args.filmIds
                    )
                }
                // add others
            }
        }
    }
}
