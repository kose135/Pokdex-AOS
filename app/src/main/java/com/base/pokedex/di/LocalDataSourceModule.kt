package com.base.pokedex.di

import com.base.pokedex.data.local.datasource.PokemonLocalDataSourceImpl
import com.base.pokedex.data.local.datasource.interfaces.PokemonLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface LocalDataSourceModule {
    @Binds
    fun bindsPokemonLocalDataSource(
        pokemonLocalDataSourceImpl: PokemonLocalDataSourceImpl
    ): PokemonLocalDataSource
}