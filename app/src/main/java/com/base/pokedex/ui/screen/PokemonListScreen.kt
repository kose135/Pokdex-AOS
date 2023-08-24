package com.base.pokedex.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.base.pokedex.R
import com.base.pokedex.data.mapper.ParsePokedex
import com.base.pokedex.data.model.local.entity.PokemonEntity
import com.base.pokedex.ui.viewmodel.PokemonViewModel
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin
import timber.log.Timber
import java.util.Locale


@Composable
fun PokemonListScreen(
    navController: NavController,
//    viewModel: PokemonViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_logo_pokemon),
                contentDescription = "Pokemon",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            PokemonList(navController = navController)
        }
    }
}

@Composable
fun PokemonList(
    navController: NavController,
    viewModel: PokemonViewModel = hiltViewModel()
) {
    val pokemonListState by remember { viewModel.pokemonListState }

    Box {
        // Vertical scroll LazyGrid
        if (pokemonListState.pokemons.isNotEmpty()!!) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                // Content padding for the grid
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                // LazyGrid supports both vertical and horizontal arrangement
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(pokemonListState.pokemons) { pokemonEntity ->
                    if ((pokemonEntity == pokemonListState.pokemons[pokemonListState.pokemons.size - 1]) && !pokemonListState.isLoading) {
                        viewModel.getPokemonList()
                    }
                    PokedexCard(navController, pokemonEntity)
                }
            }
        }
        if (pokemonListState.isLoading)
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
    }

    if (pokemonListState.error.isNotEmpty()) {
        if (pokemonListState.pokemons.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
//                    text = pokemonListState.error,
                    text = stringResource(R.string.str_error_network),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(15.dp))
                Button(onClick = {
                    viewModel.getPokemonList()
                }) {
                    Text(text = stringResource(R.string.btn_retry))
                }
            }
        } else {
            Toast.makeText(
                LocalContext.current,
                stringResource(R.string.str_retry_scroll),
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }
}

@Composable
fun PokedexCard(
    navController: NavController,
    pokemonEntity: PokemonEntity,
    modifier: Modifier = Modifier,
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    Box(
        contentAlignment = Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor,
                    )
                )
            )
            .clickable {
                Timber.i("click")
                navController.navigate(
                    "pokemon_detail_screen/${pokemonEntity.name}"
                )
            }
    ) {
        Column {
            GlideImage(
                imageModel = { pokemonEntity.imageUrl },
                component = rememberImageComponent {
                    +PalettePlugin {
                        dominantColor = ParsePokedex.calcDominantColor(it) ?: defaultDominantColor
                    }
                },
                modifier = modifier
                    .size(120.dp)
                    .align(CenterHorizontally),
                loading = {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.scale(0.5f)
                    )
                },
                failure = {
                    Image(
                        painter = painterResource(R.drawable.ic_error),
                        contentDescription = "Image load failed"
                    )
                }
            )
            Text(
                text = pokemonEntity.name
                    // convert first character upper
                    .replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.ROOT)
                        else it.toString()
                    },
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxWidth()
            )
        }
    }
}
