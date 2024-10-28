package com.example.starwars

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.starwars.navigation.PeopleScreen
import com.example.starwars.navigation.StarWarsScreen
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
                composable(route = "people") {
                    PeopleScreen(viewModel = viewModel, navController = navController)
                }
            }
        }
    }
}
