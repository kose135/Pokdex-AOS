package com.base.pokedex.data.local.datasource.interfaces

import com.base.pokedex.domain.entity.PokemonInfoEntity

interface PokemonLocalDataSource {

    suspend fun upsertPokemonInfo(pokemonInfo: PokemonInfoEntity)

    fun getPokemonInfoByName(name: String): PokemonInfoEntity?

    fun getPokemonInfoById(id: Int): PokemonInfoEntity?

}