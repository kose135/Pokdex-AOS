package com.base.pokedex.ui.screen.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.base.pokedex.domain.doOnFailure
import com.base.pokedex.domain.doOnLoading
import com.base.pokedex.domain.doOnSuccess
import com.base.pokedex.domain.usecase.GetPokemonInfoByNameUseCase
import com.base.pokedex.ui.theme.TypeNormal
import com.base.pokedex.util.PokedexParse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    arguments: SavedStateHandle,
    private val getPokemonInfoByNameUseCase: GetPokemonInfoByNameUseCase
) : ViewModel() {

    var themeColor by mutableStateOf(TypeNormal)
    var pokemonName by mutableStateOf("")

    private val _pokemonInfoState: MutableState<PokemonInfoState> =
        mutableStateOf(PokemonInfoState.Loading)
    val pokemonInfoState: State<PokemonInfoState> = _pokemonInfoState

    init {
        pokemonName = arguments.get<String>("pokemonName")?.lowercase() ?: ""
        Timber.i("pokemonName = $pokemonName")
        getPokemonInfo(pokemonName)
    }

    fun getPokemonInfo(pokemonName: String) {
        viewModelScope.launch {
            getPokemonInfoByNameUseCase(pokemonName)
                .doOnSuccess {
                    _pokemonInfoState.value = PokemonInfoState.Success(it)
                }
                .doOnFailure {
                    _pokemonInfoState.value = PokemonInfoState.Failure(it.localizedMessage!!)
                }
                .doOnLoading {
                    _pokemonInfoState.value = PokemonInfoState.Loading
                }.collect()
        }
    }

    fun setThemeColor(isDarkTheme: Boolean, palette: Palette) {
        themeColor = if (!isDarkTheme) {
            PokedexParse.calcLightVibrantColor(palette) ?: themeColor
        } else {
            PokedexParse.calcDarkMutedColor(palette) ?: themeColor
        }
    }
}