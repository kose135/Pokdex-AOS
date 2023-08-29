package com.base.pokedex.ui.screen.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.base.pokedex.domain.entity.PokemonEntity

sealed class MainNavigation(
    val route: String
) {
    object List : MainNavigation("pokemon/list")

    object Detail : MainNavigation("pokemon/detail/{pokemonName}") {
        val arguments = listOf<NamedNavArgument>(
            navArgument("pokemonName") { type = NavType.StringType }
        )

        fun moveRoute(pokemonName: String): String {
            return "pokemon/detail/$pokemonName"
        }
    }
}