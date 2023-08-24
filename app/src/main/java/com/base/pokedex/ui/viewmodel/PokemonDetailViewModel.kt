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
import com.base.pokedex.data.model.viewstate.PokemonListState
import com.base.pokedex.data.usecase.GetPokemonListUseCase
import com.base.pokedex.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    private var curPage = 0
    private var pokemonList: ArrayList<PokemonEntity> = arrayListOf()

    private val _pokemonListState: MutableState<PokemonListState> =
        mutableStateOf(PokemonListState())
    val pokemonListState: State<PokemonListState> = _pokemonListState

    init {
        getPokemonList()
    }

    fun getPokemonList() = viewModelScope.launch {
        val limit = Constants.DEFAULT_PAGE_SIZE
        val offset = curPage * limit

        getPokemonListUseCase(
            limit = limit,
            offset = offset
        )
            .doOnSuccess {
                pokemonList += it // pokemonList.addAll(it) Same
                _pokemonListState.value = PokemonListState(
                    pokemons = pokemonList.toList()
                )
                curPage++
            }
            .doOnFailure {
                _pokemonListState.value = PokemonListState(
                    pokemons = pokemonList.toList(),
                    error = it.localizedMessage!!
                )
            }
            .doOnLoading {
                _pokemonListState.value = PokemonListState(
                    pokemons = pokemonList.toList(),
                    isLoading = true
                )
            }.collect()
    }
}