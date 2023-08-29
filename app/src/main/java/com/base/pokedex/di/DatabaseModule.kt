package com.base.pokedex.di

import android.content.Context
import androidx.room.Room
import com.base.pokedex.data.local.PokedexDatabase
import com.base.pokedex.data.local.StringListPairTypeConverter
import com.base.pokedex.data.local.StringListTypeConverter
import com.base.pokedex.data.local.dao.PokemonInfoDao
import com.base.pokedex.util.Constants
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext app: Context,
        stringListTypeConverter: StringListTypeConverter,
        stringListPairTypeConverter: StringListPairTypeConverter
    ): PokedexDatabase {
        return Room.databaseBuilder(
            app,
            PokedexDatabase::class.java,
            Constants.DATABASE_NAME
        ).run {
            fallbackToDestructiveMigration()
            addTypeConverter(stringListTypeConverter)
            addTypeConverter(stringListPairTypeConverter)
            build()
        }
    }

    @Provides
    @Singleton
    fun provideStringListTypeConverter(moshi: Moshi): StringListTypeConverter {
        return StringListTypeConverter(moshi)
    }

    @Provides
    @Singleton
    fun provideStringListPairTypeConverter(): StringListPairTypeConverter {
        return StringListPairTypeConverter()
    }

    @Provides
    fun providePokemonDao(database: PokedexDatabase): PokemonInfoDao {
        return database.pokemonInfoDao()
    }

}