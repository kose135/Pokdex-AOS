package com.base.pokedex.di

import com.base.pokedex.data.remote.datasource.PokemonRemoteDataSourceImpl
import com.base.pokedex.data.remote.datasource.interfaces.PokemonRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteDataSourceModule {
    @Binds
    fun bindsPokemonRemoteDataSource(
        pokemonRemoteDataSourceImpl: PokemonRemoteDataSourceImpl
    ): PokemonRemoteDataSource
}