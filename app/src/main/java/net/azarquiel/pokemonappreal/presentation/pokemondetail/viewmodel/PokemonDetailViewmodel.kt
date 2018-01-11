package net.azarquiel.pokemonappreal.presentation.pokemondetail.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import net.azarquiel.pokemonappreal.data.local.preferences.PokemonPreferences
import net.azarquiel.pokemonappreal.domain.interactor.InteractorCallback
import net.azarquiel.pokemonappreal.domain.interactor.local.PokemonInteractor
import net.azarquiel.pokemonappreal.infraestructure.Navigator
import net.azarquiel.pokemonappreal.presentation.common.model.PokemonModel

/**
 * Created by mrmar on 10/1/18.
 */
class PokemonDetailViewmodel(val navigator:Navigator,
                             val pokemonPreferences: PokemonPreferences,
                             val pokemonInteractor: PokemonInteractor):BaseObservable(){

    val pokemon: ObservableField<PokemonModel> = ObservableField(PokemonModel())


    val isFavourite : ObservableBoolean = ObservableBoolean(false)


    fun markAsFavourite(){
        pokemonPreferences.saveFavourite(pokemon.get().id,pokemon.get().name)
    }

    fun fillPokemonView(id:Long){
        pokemonInteractor.getPokemonById(id, object :InteractorCallback<PokemonModel>{
            override fun success(data: PokemonModel?) {
                pokemon.set(data)
            }

            override fun fail(t: Throwable) {
                t.printStackTrace()
            }

        })
        isFavourite.set(pokemonPreferences.isFavourite(id))
    }
}