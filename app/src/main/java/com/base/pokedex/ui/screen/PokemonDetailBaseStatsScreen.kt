package com.base.pokedex.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.base.pokedex.data.model.local.entity.PokemonInfoEntity

@Composable
fun PokemonDetailBaseStatsScreen(
    pokemonInfoEntity: PokemonInfoEntity,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

    }

}