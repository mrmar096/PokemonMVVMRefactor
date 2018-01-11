package net.azarquiel.pokemonappreal.domain.interactor.local

import net.azarquiel.pokemonappreal.domain.interactor.InteractorCallback
import net.azarquiel.pokemonappreal.presentation.common.model.PokemonModel

/**
 * Created by mrmar on 11/1/18.
 */
interface PokemonInteractor {
    fun searchTypesByIdPokemon(id:Long,callback:InteractorCallback<MutableList<String>>)
    fun searchAbilitiesByIdPokemon(id:Long,callback:InteractorCallback<MutableList<String>>)
    fun getPokemonById(id:Long,callback:InteractorCallback<PokemonModel>)
}