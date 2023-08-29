package com.base.pokedex.data.mapper

import com.base.pokedex.data.remote.model.PokemonInfo
import com.base.pokedex.data.remote.model.Stat
import com.base.pokedex.data.remote.model.StatX
import com.base.pokedex.data.remote.model.Type
import com.base.pokedex.data.remote.model.TypeX
import com.base.pokedex.domain.entity.PokemonInfoEntity

object PokemonInfoEntityMapper :
    EntityMapper<PokemonInfo, PokemonInfoEntity> {

    override fun asEntity(domain: PokemonInfo): PokemonInfoEntity {
        return PokemonInfoEntity(
            id = domain.id,
            name = domain.name,
            imageUrl = domain.imgUrl,
            height = domain.height,
            weight = domain.weight,
            experience = domain.baseExperience,
            types = parseTypeEntity(domain.types),
            stats = parseStatEntity(domain.stats),
        )
    }

    override fun asDomain(entity: PokemonInfoEntity): PokemonInfo {
        return PokemonInfo(
            id = entity.id,
            name = entity.name,
            height = entity.height,
            weight = entity.weight,
            baseExperience = entity.experience,
            types = parseTypeDomain(entity.types),
            stats = parseStatDomain(entity.stats)
        )
    }

    private fun parseStatEntity(stats: List<Stat>): List<Pair<String, Int>> =
        stats.map { Pair(it.stat.name, it.baseStat) }

    private fun parseStatDomain(stats: List<Pair<String, Int>>): List<Stat> = stats.map {
        Stat(
            baseStat = it.second,
            effort = 0,
            stat = StatX(
                name = it.first,
                url = ""
            )
        )
    }

    private fun parseTypeEntity(types: List<Type>): List<String> =
        types.map { it.type.name }.toList()

    private fun parseTypeDomain(types: List<String>): List<Type> = types.map {
        Type(
            slot = 0,
            type = TypeX(
                name = it,
                url = ""
            )
        )
    }
}

fun PokemonInfo.asEntity(): PokemonInfoEntity {
    return PokemonInfoEntityMapper.asEntity(this)
}

fun PokemonInfoEntity.asDomain(): PokemonInfo {
    return PokemonInfoEntityMapper.asDomain(this)
}