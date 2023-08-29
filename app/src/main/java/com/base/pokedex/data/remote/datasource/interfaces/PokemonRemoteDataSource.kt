package com.base.pokedex.data.remote.datasource.interfaces

import com.base.pokedex.data.remote.model.PokemonInfo
import com.base.pokedex.data.remote.response.PokemonListResponse
import retrofit2.Response

interface PokemonRemoteDataSource {
    suspend fun getPokemonList(limit: Int, offset: Int): Response<PokemonListResponse>

    suspend fun getPokemonInfoByName(pokemonName: String): Response<PokemonInfo>

    suspend fun getPokemonInfoById(pokemonId: Int): Response<PokemonInfo>
}