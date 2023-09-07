package com.base.pokedex.presentation.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.base.pokedex.R
import com.base.pokedex.domain.entity.PokemonInfoEntity
import com.base.pokedex.presentation.screen.common.Space
import com.base.pokedex.util.PokedexParse
import java.util.Locale
import kotlin.math.round

@Composable
fun PokemonDetailAboutScreen(
    pokemonInfoEntity: PokemonInfoEntity,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Space()
        PokemonTypeSection(pokemonInfoEntity.types)
        Space()
        PokemonDetailWeightHeightSection(
            pokemonWeight = pokemonInfoEntity.weight,
            pokemonHeight = pokemonInfoEntity.height
        )
    }
}

@Composable
fun PokemonTypeSection(
    types: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.str_types),
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            for (type in types) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                        .clip(CircleShape)
                        .background(PokedexParse.parseTypeToColor(type))
                        .height(35.dp)
                ) {
                    Text(
                        text = type.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}


@Composable
fun PokemonDetailWeightHeightSection(
    pokemonWeight: Int,
    pokemonHeight: Int,
    modifier: Modifier = Modifier,
) {
    val pokemonWeightInKg = remember {
        round(pokemonWeight * 100f) / 1000f
    }
    val pokemonHeightInMeters = remember {
        round(pokemonHeight * 100f) / 1000f
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.str_weight_height),
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Space(10.dp)

        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
        ) {
            PokemonDetailDataItem(
                dataIcon = painterResource(id = R.drawable.ic_weight),
                dataItem = stringResource(id = R.string.str_weight),
                dataItemInfo = stringResource(id = R.string.str_kg).format(pokemonWeightInKg),
                modifier = Modifier
                    .weight(1f)
            )

            Spacer(
                modifier = modifier
                    .width(2.dp)
                    .fillMaxHeight()
                    .background(Color.LightGray)
            )

            PokemonDetailDataItem(
                dataIcon = painterResource(id = R.drawable.ic_height),
                dataItem = stringResource(id = R.string.str_height),
                dataItemInfo = stringResource(id = R.string.str_meter).format(pokemonHeightInMeters),
                modifier = modifier
                    .weight(1f)
            )
        }
    }
}

@Composable
fun PokemonDetailDataItem(
    dataIcon: Painter,
    dataItem: String,
    dataItemInfo: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Icon(
            painter = dataIcon,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
        )
        Space(8.dp)
        Text(
            text = dataItem,
            color = Color.Gray,
            fontSize = 15.sp,
        )
        Space(4.dp)
        Text(
            text = dataItemInfo,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
        )
    }
}
