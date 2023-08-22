package com.base.pokedex.util

import androidx.compose.ui.graphics.Color
import com.base.pokedex.data.model.remote.dto.Type
import com.base.pokedex.ui.theme.*
import java.util.Locale

object PokedexOption {

    fun parseTypeToColor(type: Type): Color {
        return when (type.type.name.toLowerCase(Locale.ROOT)) {
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

    fun parseStatToColor(stat: String): Color {
        return when (stat.lowercase(Locale.getDefault())) {
            "hp" -> HPColor
            "attack" -> AtkColor
            "defense" -> DefColor
            "special-attack" -> SpAtkColor
            "special-defense" -> SpDefColor
            "speed" -> SpdColor
            else -> Color.White
        }
    }

    fun parseStatToAbbr(stat: String): String {
        return when (stat.lowercase(Locale.getDefault())) {
            "hp" -> "HP"
            "attack" -> "Attack"
            "defense" -> "Defense"
            "special-attack" -> "Special-Attack"
            "special-defense" -> "Special-Defense"
            "speed" -> "Speed"
            else -> ""
        }
    }
}