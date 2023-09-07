package com.base.pokedex.presentation.screen.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

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