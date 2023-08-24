package com.base.pokedex.data.mapper

import com.base.pokedex.data.model.local.entity.PokemonInfoEntity
import com.base.pokedex.data.model.remote.dto.PokemonInfo

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
            types = ParsePokedex.parseTypeEntity(domain.types),
            hp = ParsePokedex.parseStatEntity(ParsePokedex.HP, domain.stats),
            attack = ParsePokedex.parseStatEntity(ParsePokedex.ATTACK, domain.stats),
            defense = ParsePokedex.parseStatEntity(ParsePokedex.DEFENSE, domain.stats),
            specialAttack = ParsePokedex.parseStatEntity(ParsePokedex.SPECIAL_ATTACK, domain.stats),
            specialDefense = ParsePokedex.parseStatEntity(
                ParsePokedex.SPECIAL_DEFENSE,
                domain.stats
            ),
            speed = ParsePokedex.parseStatEntity(ParsePokedex.SPEED, domain.stats),
        )
    }

    override fun asDomain(entity: PokemonInfoEntity): PokemonInfo {
        return PokemonInfo(
            id = entity.id,
            name = entity.name,
            height = entity.height,
            weight = entity.weight,
            baseExperience = entity.experience,
            types = ParsePokedex.parseTypeDomain(entity.types),
            stats = ParsePokedex.parseStatDomain(
                hp = entity.hp,
                attack = entity.attack,
                defense = entity.defense,
                specialAttack = entity.specialAttack,
                specialDefense = entity.specialDefense,
                speed = entity.speed,
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