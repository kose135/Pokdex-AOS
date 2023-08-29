package com.base.pokedex.data.remote

import com.base.pokedex.data.remote.model.PokemonInfo
import com.base.pokedex.data.remote.response.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonListResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonInfoByName(
        @Path("name") name: String
    ): Response<PokemonInfo>

    @GET("pokemon/{id}")
    suspend fun getPokemonInfoById(
        @Path("id") id: Int
    ): Response<PokemonInfo>
}