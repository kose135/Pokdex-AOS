package com.base.pokedex.data.model.local

import androidx.room.Database
import androidx.room.ProvidedTypeConverter
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.base.pokedex.data.model.local.dao.PokemonInfoDao
import com.base.pokedex.data.model.local.entity.PokemonInfoEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@Database(
    entities = [PokemonInfoEntity::class],
    version = 1,
)
@TypeConverters(StringListTypeConverter::class)
abstract class PokedexDatabase : RoomDatabase() {

    abstract fun pokemonInfoDao(): PokemonInfoDao
}

@ProvidedTypeConverter
class StringListTypeConverter @Inject constructor(
    private val moshi: Moshi
) {
    @TypeConverter
    fun fromString(value: String): List<String>? {
        val listType = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(type: List<String>): String {
        val listType = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}