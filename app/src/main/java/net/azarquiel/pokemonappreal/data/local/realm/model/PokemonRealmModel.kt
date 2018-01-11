package net.azarquiel.pokemonappreal.data.local.realm.model

/**
 * Created by mrmar on 10/1/18.
 */
data class PokemonRealmModel(
        var id : Long = -1L,
        var name: String = "",
        var abilities: MutableList<String> = mutableListOf(),
        var types: MutableList<String> = mutableListOf()
)