package com.base.pokedex.data.mapper

import com.base.pokedex.data.remote.dto.Pokemon
import com.base.pokedex.domain.entity.PokemonEntity


object PokemonEntityMapper : EntityMapper<List<Pokemon>, List<PokemonEntity>> {

    override fun asEntity(domain: List<Pokemon>): List<PokemonEntity> {
        return domain.map { pokemon ->
            PokemonEntity(
                name = pokemon.name,
                url = pokemon.url,
                imageUrl = pokemon.imgUrl
            )
        }
    }

    override fun asDomain(entity: List<PokemonEntity>): List<Pokemon> {
        return entity.map { pokemonEntity ->
            Pokemon(
                name = pokemonEntity.name,
                url = pokemonEntity.url
            )
        }
    }
}

fun List<Pokemon>.asEntity(): List<PokemonEntity> {
    return PokemonEntityMapper.asEntity(this)
}

fun List<PokemonEntity>?.asDomain(): List<Pokemon> {
    return PokemonEntityMapper.asDomain(this.orEmpty())
}