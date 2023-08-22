package com.base.pokedex.data.mapper

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette
import com.base.pokedex.data.model.remote.dto.Stat
import com.base.pokedex.data.model.remote.dto.StatX
import com.base.pokedex.data.model.remote.dto.Type
import com.base.pokedex.data.model.remote.dto.TypeX

object ParsePokedex {

    const val HP = "hp"
    const val ATTACK = "attack"
    const val DEFENSE = "defense"
    const val SPECIAL_ATTACK = "special-attack"
    const val SPECIAL_DEFENSE = "special-defense"
    const val SPEED = "speed"

    fun parseStatEntity(statOption: String, stats: List<Stat>): Int {
        return (stats.find { it.stat.name == statOption } ?: 0) as Int
    }

    fun parseStatDomain(
        hp: Int,
        attack: Int,
        defense: Int,
        specialAttack: Int,
        specialDefense: Int,
        speed: Int
    ): List<Stat> {
        return listOf(
            Stat(
                baseStat = hp,
                effort = 0,
                stat = StatX(
                    name = HP,
                    url = ""
                )
            ),
            Stat(
                baseStat = attack,
                effort = 0,
                stat = StatX(
                    name = ATTACK,
                    url = ""
                )
            ),
            Stat(
                baseStat = defense,
                effort = 0,
                stat = StatX(
                    name = DEFENSE,
                    url = ""
                )
            ),
            Stat(
                baseStat = specialAttack,
                effort = 0,
                stat = StatX(
                    name = SPECIAL_ATTACK,
                    url = ""
                )
            ),
            Stat(
                baseStat = specialDefense,
                effort = 0,
                stat = StatX(
                    name = SPECIAL_DEFENSE,
                    url = ""
                )
            ),
            Stat(
                baseStat = speed,
                effort = 0,
                stat = StatX(
                    name = SPEED,
                    url = ""
                )
            )
        )
    }

    fun parseTypeEntity(types: List<Type>): List<String> {
        return types.map { it.type.name }.toList()
    }

    fun parseTypeDomain(types: List<String>): List<Type> {
        val typeArrayList = arrayListOf<Type>()

        for (type in types) {
            val type = Type(
                slot = 0,
                type = TypeX(
                    name = type,
                    url = ""
                )
            )
            typeArrayList.add(type)
        }

        return typeArrayList.toList()
    }

    /**
     * lightVibrantSwatch - 밝고 생생함
     * vibrantSwatch - 생생함
     * darkVibrantSwatch - 어둡고 생생함
     * lightMutedSwatch - 밝고 수수함
     * mutedSwatch - 수수함
     * darkMutedSwatch - 어둡고 수수함
     * dominantSwatch - 이미지에서 가장 빈도가 높은 색상
     */
    fun calcDominantColor(palette: Palette?): Color? {
        val intColor = palette?.dominantSwatch?.rgb

        return intToColor(intColor)
    }

    private fun intToColor(intColor: Int?): Color? {
        return intColor?.let { Color(it) }
    }


}