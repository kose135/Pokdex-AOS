package com.base.pokedex.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.pokedex.data.model.local.entity.PokemonEntity
import com.base.pokedex.data.model.remote.doOnFailure
import com.base.pokedex.data.model.remote.doOnLoading
import com.base.pokedex.data.model.remote.doOnSuccess
import com.base.pokedex.data.model.viewstate.PokemonInfoState
import com.base.pokedex.data.model.viewstate.PokemonListState
import com.base.pokedex.data.usecase.GetPokemonInfoByNameUseCase
import com.base.pokedex.data.usecase.GetPokemonListUseCase
import com.base.pokedex.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonInfoByNameUseCase: GetPokemonInfoByNameUseCase
) : ViewModel() {

    private val _pokemonInfoState: MutableState<PokemonInfoState> =
        mutableStateOf(PokemonInfoState())
    val pokemonInfoState: State<PokemonInfoState> = _pokemonInfoState

    fun getPokemonInfo(pokemonName: String) {
        viewModelScope.launch {
            getPokemonInfoByNameUseCase(pokemonName)
                .doOnSuccess {
                    _pokemonInfoState.value = PokemonInfoState(
                        pokemonInfo = it
                    )
                }
                .doOnFailure {
                    _pokemonInfoState.value = PokemonInfoState(
                        error = it.localizedMessage!!
                    )
                }
                .doOnFailure {
                    _pokemonInfoState.value = PokemonInfoState(
                        isLoading = true
                    )
                }.collect()
        }
    }
}