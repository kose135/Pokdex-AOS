package com.base.pokedex.ui.screen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.base.pokedex.R
import com.base.pokedex.data.mapper.ParsePokedex
import com.base.pokedex.data.model.local.entity.PokemonInfoEntity
import com.base.pokedex.data.model.view.NoInteraction
import com.base.pokedex.data.model.view.TabRowItem
import com.base.pokedex.ui.theme.TypeNormal
import com.base.pokedex.ui.viewmodel.PokemonDetailViewModel
import com.base.pokedex.util.offsetForPage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Locale
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun PokemonDetailScreen(
    navController: NavController,
    pokemonName: String,
    modifier: Modifier = Modifier,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    Timber.i("pokemonName = $pokemonName")
    val pokemonInfoState by remember { viewModel.pokemonInfoState }
    viewModel.getPokemonInfo(pokemonName)

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        if (pokemonInfoState.pokemonInfo != null) {
            PokemonDetailSection(pokemonInfoState.pokemonInfo!!)
        }

        if (pokemonInfoState.error.isNotEmpty()) {
            Column(
                modifier = modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
//                    text = pokemonInfoState.error,
                    text = stringResource(R.string.str_error_network),
                    fontWeight = FontWeight.Bold
                )
                Spacer(
                    modifier = modifier
                        .height(15.dp)
                )
                Button(onClick = {
                    viewModel.getPokemonInfo(pokemonName)
                }) {
                    Text(text = stringResource(R.string.btn_retry))
                }
            }
        }

        if (pokemonInfoState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        PokemonDetailTopSection(
            navController = navController,
        )
    }
}

@Composable
fun PokemonDetailTopSection(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(36.dp)
                .clickable {
                    navController.popBackStack()
                }
        )

        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(25.dp)
                .clickable {

                }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonDetailSection(
    pokemonInfoEntity: PokemonInfoEntity,
    pokemonImageSize: Dp = 200.dp,
    topPadding: Dp = 50.dp,
    modifier: Modifier = Modifier,
) {
    var lightMutedColor by remember {
        mutableStateOf(TypeNormal)
    }

    val tabRowItems: List<TabRowItem> = listOf(
        TabRowItem(
            title = stringResource(id = R.string.tab_about),
            screen = { PokemonDetailAboutScreen(pokemonInfoEntity) },
        ),
        TabRowItem(
            title = stringResource(id = R.string.tab_base_stats),
            screen = { PokemonDetailBaseStatsScreen(pokemonInfoEntity) },
        ),
    )
    val pagerState = rememberPagerState(
        initialPage = 0, // start page
//            initialPageOffsetFraction = 0f,
        pageCount = {
            tabRowItems.size
        }
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(lightMutedColor)
            .padding(top = topPadding)
            .fillMaxSize()
    ) {
        // Pokemon Name & Id Section
        PokemonDetailNameIdSection(
            pokemonInfoEntity.name,
            pokemonInfoEntity.id
        )

        // Profile Image Section
        Box(
            modifier = Modifier
                .height(pokemonImageSize)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val infiniteTransition = rememberInfiniteTransition(label = "")
            val rotation by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 3000,
                        easing = LinearEasing,
                    ),
                ), label = ""
            )

            Image(
                painter = painterResource(id = R.drawable.ic_pokeball),
                contentDescription = null,
                modifier = Modifier
                    .alpha(0.1f)
                    .size(pokemonImageSize * 2)
                    .background(Color.Transparent)
                    .graphicsLayer(rotationZ = rotation)
            )

            GlideImage(
                imageModel = { pokemonInfoEntity.imageUrl },
                modifier = modifier
                    .size(pokemonImageSize),
                component = rememberImageComponent {
                    +PalettePlugin {
                        lightMutedColor =
                            ParsePokedex.calcLightMutedSwatchColor(it) ?: lightMutedColor
                    }
                },
                failure = {
                    Image(
                        painter = painterResource(R.drawable.ic_error),
                        contentDescription = "Image load failed"
                    )
                }
            )
        }

//        PokemonDetailProfileImageSection(
//            imageUrl = pokemonInfoEntity.imageUrl,
//            lightMutedColor = lightMutedColor,
//        )

        Spacer(
            modifier = modifier
                .padding(top = 40.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 40.dp, topEnd = 50.dp)
                )
                .height(50.dp)
                .fillMaxWidth()
        )

        // Tab layout Section
        PokemonDetailTabLayoutSection(
            pages = tabRowItems.map { it.title },
            pagerState = pagerState,
            indicatorColor = lightMutedColor
        )

        // Paging Section
        PokemonDetailPagerContentSection(
            pagerState = pagerState,
            tabRowItems = tabRowItems
        )
    }
}

@Composable
fun PokemonDetailNameIdSection(
    pokemonName: String,
    pokemonId: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = pokemonName.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            },
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.White,
            modifier = Modifier
                .padding(end = 16.dp)
        )

        Text(
            text = stringResource(id = R.string.str_pokemon_id)
                .format(pokemonId),
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .padding(end = 16.dp)
        )
    }
}

//@Composable
//fun PokemonDetailProfileImageSection(
//    imageUrl: String,
//    lightMutedColor: Color,
//    pokemonImageSize: Dp = 200.dp,
//    modifier: Modifier = Modifier,
//) {
//    GlideImage(
//        imageModel = { imageUrl },
//        modifier = modifier
//            .size(pokemonImageSize),
//        component = rememberImageComponent {
//            +PalettePlugin {
//                lightMutedColor = ParsePokedex.calcLightMutedSwatchColor(it) ?: lightMutedColor
//            }
//        },
//        failure = {
//            Image(
//                painter = painterResource(R.drawable.ic_error),
//                contentDescription = "Image load failed"
//            )
//        }
//    )
//}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonDetailTabLayoutSection(
    pages: List<String>,
    pagerState: PagerState,
    indicatorColor: Color = TypeNormal,
    width: Float = 20f
) {
    val scope = rememberCoroutineScope()
    val sizeList = remember { mutableStateMapOf<Int, Pair<Float, Float>>() }
    val progressFromFirstPage by remember {
        derivedStateOf {
            pagerState.offsetForPage(0)
        }
    }

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        contentColor = Color(0xFF362C28),
        divider = {},
        indicator = {
            Box(modifier = Modifier
//                    .padding(start = 20.dp)
                .fillMaxSize()
                .drawBehind {
                    val ribbonSectionsLengths = mutableMapOf<Int, Float>()
                    var currentRibbonLength = 0f

                    var currentOrigin = 0f
                    val path = Path()

                    sizeList.keys
                        .sorted()
                        .mapNotNull { sizeList[it] }
                        .forEachIndexed { index, (width, height) ->
                            val bottom = height - 10f
                            val top = 10f

                            if (index == 0) path.moveTo(0f, top)

                            // top - right
                            path.quadraticBezierTo(
                                currentOrigin + width,
                                top,
                                currentOrigin + width,
                                height / 2,
                            )

                            // bottom - right
                            path.quadraticBezierTo(
                                currentOrigin + width,
                                bottom,
                                currentOrigin + (width / 2),
                                bottom,
                            )

                            // left?
                            path.quadraticBezierTo(
                                currentOrigin + 0f,
                                bottom,
                                currentOrigin + 0f,
                                height / 2,
                            )

                            // top - left
                            path.quadraticBezierTo(
                                currentOrigin - 10f,
                                top,
                                currentOrigin + width,
                                top,
                            )

                            currentOrigin += width

                            val measure = PathMeasure()
                            measure.setPath(path, false)

                            val length = measure.length
                            ribbonSectionsLengths[index] = length - currentRibbonLength
                            currentRibbonLength = length
                        }

                    val progress = progressFromFirstPage - floor(progressFromFirstPage)
                    val start = floor(progressFromFirstPage)
                        .toInt()
                        .coerceIn(0, ribbonSectionsLengths.size - 1)
                    val end = ceil(progressFromFirstPage)
                        .toInt()
                        .coerceIn(0, ribbonSectionsLengths.size - 1)

                    val ribbonLength =
                        ribbonSectionsLengths[start]!! + ((ribbonSectionsLengths[end]!! - ribbonSectionsLengths[start]!!) * progress)

                    val lengthUntilStart = ribbonSectionsLengths
                        .keys
                        .sorted()
                        .map { ribbonSectionsLengths[it] ?: 0f }
                        .take(start)
                        .fold(0f) { acc, it -> acc - it }

                    val lengthUntilEnd = ribbonSectionsLengths
                        .keys
                        .sorted()
                        .map { ribbonSectionsLengths[it] ?: 0f }
                        .take(end)
                        .fold(0f) { acc, it -> acc - it }

                    val phaseOffset =
                        lengthUntilStart + ((lengthUntilEnd - lengthUntilStart) * progress)

                    drawPath(
                        path = path,
                        color = indicatorColor,
                        style = Stroke(
                            width = width, // 두께
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round,
                            pathEffect = PathEffect.dashPathEffect(
                                intervals = floatArrayOf(
                                    ribbonLength, currentRibbonLength
                                ),
                                phase = phaseOffset,
                            )
                        )
                    )
                }
            )
        }
    ) {
        pages.forEachIndexed { index, title ->
            Tab(
                selected = index == pagerState.currentPage,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                modifier = Modifier
                    .onSizeChanged {
                        sizeList[index] = Pair(it.width.toFloat(), it.height.toFloat())
                    },
                interactionSource = remember { NoInteraction() }
            ) {
                Text(
                    text = title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonDetailPagerContentSection(
    pagerState: PagerState,
    tabRowItems: List<TabRowItem>,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        state = pagerState,
        beyondBoundsPageCount = 1, // 목록 앞뒤에 로드할 페이지
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
    ) { pagePosition ->
        tabRowItems[pagePosition].screen()
    }
}

