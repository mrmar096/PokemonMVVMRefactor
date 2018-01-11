package net.azarquiel.pokemonappreal.presentation.common.model

/**
 * Created by mrmar on 10/1/18.
 */
data class PokemonModel(
        var id : Long = -1L,
        var name: String = "",
        var abilities: MutableList<String> = mutableListOf(),
        var types: MutableList<String> = mutableListOf()
)