package com.example.starwars.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.starwars.R
import com.example.starwars.model.Person
import com.example.starwars.navigation.FilmScreen

@Composable
fun CharacterListItem(
    person: Person,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var readMore by remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = person.name,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = stringResource(R.string.character_gender_info) + person.gender,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(R.string.character_year_of_birth_info) + person.birthYear,
                style = MaterialTheme.typography.bodyLarge
            )
            HorizontalDivider()
            Text(
                text = stringResource(R.string.character_height_info) + person.height.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(R.string.character_mass_info) + person.mass.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
            HorizontalDivider()
            Text(
                text = stringResource(R.string.character_hair_color_info) + person.hairColor,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(R.string.character_skin_color_info) + person.skinColor,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(R.string.character_eye_color_info) + person.eyeColor,
                style = MaterialTheme.typography.bodyLarge
            )
            AnimatedVisibility(
                visible = readMore.not(),
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut(),
                modifier = Modifier.align(Alignment.End)
            ) {
                Button(
                    onClick = { readMore = true }
                ) {
                    Text(text = stringResource(R.string.read_more))
                }
            }
            AnimatedVisibility(
                visible = readMore,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut(),
                modifier = Modifier.padding(16.dp)
            ) {
                Column {
                    ReadMoreInfo(
                        text = "Films",
                        onClick = {
                            val filmIds = extractFilmIds(person.films)
                            navController.navigate(FilmScreen(filmIds))
                        },
                        contentDescription = "Open films"
                    )
                    ReadMoreInfo(
                        text = "Species",
                        onClick = { },
                        contentDescription = "Open species"
                    )
                    ReadMoreInfo(
                        text = "Vehicles",
                        onClick = { },
                        contentDescription = "Open vehicles"
                    )
                    ReadMoreInfo(
                        text = "Starships",
                        onClick = { },
                        contentDescription = "Open starships"
                    )
                }
            }
        }
    }
}

fun extractFilmIds(urls: List<String>): List<Int> {
    val regex = Regex("(?<=\\/)\\d+(?=\\/?\$)")
    return urls.mapNotNull { url ->
        regex.find(url)?.value?.removeSurrounding("/")?.toIntOrNull()
    }
}

@Preview(
    backgroundColor = 0xFF121212,
    showBackground = true
)
@Composable
private fun CharacterListItemPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        CharacterListItem(
            person = Person(
                name = "Luke Skywalker",
                height = 172,
                mass = 77,
                hairColor = "blond",
                skinColor = "fair",
                eyeColor = "blue",
                birthYear = "19BBY",
                gender = "male",
                films = listOf("bla"),
                species = listOf("bla"),
                vehicles = listOf("bla"),
                starships = listOf("bla"),
            ),
            rememberNavController()
        )
    }
}
