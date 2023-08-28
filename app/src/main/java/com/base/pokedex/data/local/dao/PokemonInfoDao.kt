package com.base.pokedex.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.base.pokedex.domain.entity.PokemonInfoEntity

@Dao
interface PokemonInfoDao {

    @Upsert // insert & update
    suspend fun upsertPokemonInfo(pokemonInfo: PokemonInfoEntity)

    @Query("SELECT * FROM PokemonInfoEntity WHERE name = :name")
    fun getPokemonInfoByName(name: String): PokemonInfoEntity?

    @Query("SELECT * FROM PokemonInfoEntity WHERE id = :id")
    fun getPokemonInfoById(id: Int): PokemonInfoEntity?
}