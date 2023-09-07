package com.base.pokedex.presentation.screen.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.pokedex.domain.doOnFailure
import com.base.pokedex.domain.doOnLoading
import com.base.pokedex.domain.doOnSuccess
import com.base.pokedex.domain.entity.PokemonEntity
import com.base.pokedex.domain.usecase.GetPokemonListUseCase
import com.base.pokedex.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    private var curPage = 0
    private var pokemonList: ArrayList<PokemonEntity> = arrayListOf()

    private val _pokemonListState: MutableState<PokemonListState> =
        mutableStateOf(PokemonListState.Loading)
    val pokemonListState: State<PokemonListState> = _pokemonListState

    init {
        fetchNextPokemonList()
    }

    fun fetchNextPokemonList() {
        viewModelScope.launch {
            val limit = Constants.DEFAULT_PAGE_SIZE
            val offset = curPage * limit

            getPokemonListUseCase(
                limit = limit,
                offset = offset
            )
                .doOnSuccess {
                    pokemonList += it // pokemonList.addAll(it) Same
                    _pokemonListState.value = PokemonListState.Success(pokemonList.toList())
                    curPage++
                }
                .doOnFailure {
                    _pokemonListState.value =
                        PokemonListState.Failure(it.localizedMessage!!)
                }
                .doOnLoading {
                    _pokemonListState.value = PokemonListState.Loading
                }.collect()
        }
    }
}
