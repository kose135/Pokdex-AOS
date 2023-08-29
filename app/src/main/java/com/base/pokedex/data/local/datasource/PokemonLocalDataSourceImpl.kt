package com.base.pokedex.data.local.datasource

import com.base.pokedex.data.local.dao.PokemonInfoDao
import com.base.pokedex.data.local.datasource.interfaces.PokemonLocalDataSource
import com.base.pokedex.domain.entity.PokemonInfoEntity
import javax.inject.Inject

class PokemonLocalDataSourceImpl @Inject constructor(
    private val dao: PokemonInfoDao
) : PokemonLocalDataSource {
    override suspend fun upsertPokemonInfo(pokemonInfoEntity: PokemonInfoEntity) =
        dao.upsertPokemonInfo(pokemonInfoEntity)

    override fun getPokemonInfoByName(pokemonName: String): PokemonInfoEntity? =
        dao.getPokemonInfoByName(pokemonName)

    override fun getPokemonInfoById(pokemonId: Int): PokemonInfoEntity? =
        dao.getPokemonInfoById(pokemonId)
}