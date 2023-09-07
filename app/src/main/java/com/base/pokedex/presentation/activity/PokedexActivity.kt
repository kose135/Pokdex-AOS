package com.base.pokedex.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.base.pokedex.presentation.screen.detail.PokemonDetailScreen
import com.base.pokedex.presentation.screen.list.PokemonListScreen
import com.base.pokedex.presentation.screen.navigation.MainNavigation
import com.base.pokedex.presentation.theme.PokedexApplicationTheme
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
                    startDestination = MainNavigation.List.route
                ) {
                    composable(
                        MainNavigation.List.route,
                        enterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                                animationSpec = tween(300)
                            )
                        },
                        exitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                                animationSpec = tween(300)
                            )
                        },
                    ) {
                        PokemonListScreen(navController = navHostController)
                    }
                    composable(
                        MainNavigation.Detail.route,
                        arguments = MainNavigation.Detail.arguments,
                        enterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                                animationSpec = tween(300)
                            )
                        },
                        exitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                                animationSpec = tween(300)
                            )
                        },
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
