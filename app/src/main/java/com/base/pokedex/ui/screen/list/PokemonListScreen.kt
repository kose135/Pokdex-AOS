package com.base.pokedex.ui.screen.list

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
import com.base.pokedex.domain.entity.PokemonEntity
import com.base.pokedex.ui.screen.common.LoadingIndicator
import com.base.pokedex.util.PokedexParse
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin
import timber.log.Timber
import java.util.Locale


@Composable
fun PokemonListScreen(
    navController: NavController,
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
            PokemonListView(navController = navController)
        }
    }
}

@Composable
fun PokemonListView(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val pokemonListState = viewModel.pokemonListState.value
    var pokemons by remember { mutableStateOf(emptyList<PokemonEntity>()) }
    var isLoading by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        /*
         * 같은 List를 공유해야 LazyVerticalGrid가 refresh를 하지 않고 같은 scroll position에 위치하고 있음
         * 그래서 List를 같이 사용할 있고 pokemonListState가 있는 함수에서 선언하고 사용
         */
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            // Content padding for the grid
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            // LazyGrid supports both vertical and horizontal arrangement
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(pokemons.size) { index ->
                val pokemonEntity = pokemons[index]

                if ((pokemonEntity == pokemons[pokemons.size - 2]) && !isLoading) {
                    viewModel.fetchNextPokemonList()
                }
                PokemonItem(navController, pokemonEntity)
            }
        }

        when (pokemonListState) {
            is PokemonListState.Success -> {
                pokemons = pokemonListState.pokemons
                isLoading = false
            }

            is PokemonListState.Failure -> {
                val errorMessage = pokemonListState.message
                isLoading = false

                if (pokemons.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
//                    text = pokemonListState.errorMessage,
                            text = stringResource(R.string.str_error_network),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Button(onClick = {
                            viewModel.fetchNextPokemonList()
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

            is PokemonListState.Loading -> {
                isLoading = true
                LoadingIndicator()
            }
        }
    }
}

@Composable
fun PokemonItem(
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
                        dominantColor = PokedexParse.calcDominantColor(it) ?: defaultDominantColor
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
