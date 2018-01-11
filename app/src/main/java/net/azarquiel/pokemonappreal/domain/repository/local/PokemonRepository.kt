package net.azarquiel.pokemonappreal.domain.repository.local

import net.azarquiel.pokemonappreal.data.local.realm.model.PokemonRealmModel
import net.azarquiel.pokemonappreal.domain.repository.RepositoryCallback

/**
 * Created by mrmar on 11/1/18.
 */
interface PokemonRepository {
    fun searchTypesByIdPokemon(id:Long,callback:RepositoryCallback<MutableList<String>>)
    fun searchAbilitiesByIdPokemon(id:Long,callback:RepositoryCallback<MutableList<String>>)
    fun getPokemonById(id:Long,callback:RepositoryCallback<PokemonRealmModel>)

}