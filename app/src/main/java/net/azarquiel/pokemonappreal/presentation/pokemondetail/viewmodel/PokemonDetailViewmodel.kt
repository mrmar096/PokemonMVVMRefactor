package net.azarquiel.pokemonappreal.presentation.pokemondetail.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableField
import net.azarquiel.pokemonappreal.infraestructure.Navigator
import net.azarquiel.pokemonappreal.presentation.pokemondetail.model.PokemonDetailModel

/**
 * Created by mrmar on 10/1/18.
 */
class PokemonDetailViewmodel(navigator:Navigator):BaseObservable(){
    val pokemonDetail : ObservableField<PokemonDetailModel> = ObservableField()

}