package net.azarquiel.pokemonappreal.domain.interactor.local

import net.azarquiel.pokemonappreal.data.local.realm.model.PokemonRealmModel
import net.azarquiel.pokemonappreal.domain.interactor.InteractorCallback
import net.azarquiel.pokemonappreal.domain.repository.RepositoryCallback
import net.azarquiel.pokemonappreal.domain.repository.local.PokemonRepository
import net.azarquiel.pokemonappreal.presentation.common.model.PokemonModel

/**
 * Created by mrmar on 11/1/18.
 */
class PokemonInteractorImpl(val pokemonRepository: PokemonRepository) : PokemonInteractor {

    override fun searchTypesByIdPokemon(id: Long, callback: InteractorCallback<MutableList<String>>) {
        callback.before()
        pokemonRepository.searchTypesByIdPokemon(id,object :RepositoryCallback<MutableList<String>>{
            override fun success(data: MutableList<String>?) {
                callback.success(data)
                callback.finish()
            }

            override fun fail(t: Throwable) {
                callback.fail(t)
                callback.finish()
            }

        })
    }

    override fun searchAbilitiesByIdPokemon(id: Long, callback: InteractorCallback<MutableList<String>>) {
        callback.before()
        pokemonRepository.searchAbilitiesByIdPokemon(id,object :RepositoryCallback<MutableList<String>>{
            override fun success(data: MutableList<String>?) {
                callback.success(data)
                callback.finish()
            }

            override fun fail(t: Throwable) {
                callback.fail(t)
                callback.finish()
            }

        })
    }

    override fun getPokemonById(id: Long, callback: InteractorCallback<PokemonModel>) {
        callback.before()
        pokemonRepository.getPokemonById(id,object : RepositoryCallback<PokemonRealmModel>{
            override fun success(data: PokemonRealmModel?) {
                var pokemon : PokemonModel? = null
                data?.let {
                    pokemon = PokemonModel(it.id,it.name,it.abilities,it.types)
                }
                callback.success(pokemon)
            }

            override fun fail(t: Throwable) {

            }

        })
    }
}