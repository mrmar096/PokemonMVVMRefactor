package net.azarquiel.pokemonappreal.infraestructure.utils

import net.azarquiel.pokemonappreal.presentation.common.model.PokemonTypes

/**
 * Created by mrmar on 14/1/18.
 */
object PokemonFormatter{
        @JvmStatic
        fun formatPokemonTypes(mutableList: MutableList<PokemonTypes>):MutableList<String> =
                mutableList.sortedBy { it.order }.map { it.nameType }.toMutableList()
}

