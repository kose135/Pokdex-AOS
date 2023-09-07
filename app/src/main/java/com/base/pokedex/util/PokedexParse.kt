package com.base.pokedex.util

import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette
import com.base.pokedex.presentation.theme.AtkColor
import com.base.pokedex.presentation.theme.DefColor
import com.base.pokedex.presentation.theme.HPColor
import com.base.pokedex.presentation.theme.SpAtkColor
import com.base.pokedex.presentation.theme.SpDefColor
import com.base.pokedex.presentation.theme.SpdColor
import com.base.pokedex.presentation.theme.TypeBug
import com.base.pokedex.presentation.theme.TypeDark
import com.base.pokedex.presentation.theme.TypeDragon
import com.base.pokedex.presentation.theme.TypeElectric
import com.base.pokedex.presentation.theme.TypeFairy
import com.base.pokedex.presentation.theme.TypeFighting
import com.base.pokedex.presentation.theme.TypeFire
import com.base.pokedex.presentation.theme.TypeFlying
import com.base.pokedex.presentation.theme.TypeGhost
import com.base.pokedex.presentation.theme.TypeGrass
import com.base.pokedex.presentation.theme.TypeGround
import com.base.pokedex.presentation.theme.TypeIce
import com.base.pokedex.presentation.theme.TypeNormal
import com.base.pokedex.presentation.theme.TypePoison
import com.base.pokedex.presentation.theme.TypePsychic
import com.base.pokedex.presentation.theme.TypeRock
import com.base.pokedex.presentation.theme.TypeSteel
import com.base.pokedex.presentation.theme.TypeWater

object PokedexParse {

    const val HP = "hp"
    const val ATTACK = "attack"
    const val DEFENSE = "defense"
    const val SPECIAL_ATTACK = "special-attack"
    const val SPECIAL_DEFENSE = "special-defense"
    const val SPEED = "speed"

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

    fun calcLightVibrantColor(palette: Palette?): Color? {
        val intColor = palette?.lightVibrantSwatch?.rgb
        return intToColor(intColor)
    }

    fun calcDarkMutedColor(palette: Palette?): Color? {
        val intColor = palette?.darkMutedSwatch?.rgb
        return intToColor(intColor)
    }

    private fun intToColor(intColor: Int?): Color? {
        return intColor?.let { Color(it) }
    }

    fun parseTypeToColor(type: String): Color {
        return when (type.lowercase()) {
            "normal" -> TypeNormal
            "fire" -> TypeFire
            "water" -> TypeWater
            "electric" -> TypeElectric
            "grass" -> TypeGrass
            "ice" -> TypeIce
            "fighting" -> TypeFighting
            "poison" -> TypePoison
            "ground" -> TypeGround
            "flying" -> TypeFlying
            "psychic" -> TypePsychic
            "bug" -> TypeBug
            "rock" -> TypeRock
            "ghost" -> TypeGhost
            "dragon" -> TypeDragon
            "dark" -> TypeDark
            "steel" -> TypeSteel
            "fairy" -> TypeFairy
            else -> Color.Black
        }
    }

    fun parseStatToAbbr(stat: String): String {
        return when (stat.lowercase()) {
            HP -> "HP"
            ATTACK -> "ATK"
            DEFENSE -> "DEF"
            SPECIAL_ATTACK -> "SP ATK"
            SPECIAL_DEFENSE -> "SP DEF"
            SPEED -> "SPD"
            else -> ""
        }
    }

    fun parseStatToColor(stat: String): Color {
        return when (stat.lowercase()) {
            HP -> HPColor
            ATTACK -> AtkColor
            DEFENSE -> DefColor
            SPECIAL_ATTACK -> SpAtkColor
            SPECIAL_DEFENSE -> SpDefColor
            SPEED -> SpdColor
            else -> Color.White
        }
    }
}