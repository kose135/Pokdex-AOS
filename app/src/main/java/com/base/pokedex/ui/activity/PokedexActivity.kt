package com.base.pokedex.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.base.pokedex.ui.screen.detail.PokemonDetailScreen
import com.base.pokedex.ui.screen.list.PokemonListScreen
import com.base.pokedex.ui.theme.PokedexApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokedexActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexApplicationTheme {
                val navHostController = rememberNavController()

                NavHost(
                    navController = navHostController,
                    startDestination = "pokemon_list_screen"
                ) {
                    composable("pokemon_list_screen") {
                        PokemonListScreen(navController = navHostController)
                    }
                    composable(
                        "pokemon_detail_screen/{pokemonName}",
                        arguments = listOf(
                            navArgument("pokemonName") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        PokemonDetailScreen(
                            navController = navHostController
                        )
                    }

                }
            }
        }
    }
}
