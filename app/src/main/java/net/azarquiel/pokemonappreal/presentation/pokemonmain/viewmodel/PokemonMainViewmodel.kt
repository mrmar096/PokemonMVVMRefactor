package net.azarquiel.pokemonappreal.presentation.pokemonmain.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.widget.RadioGroup
import net.azarquiel.pokemonappreal.R
import net.azarquiel.pokemonappreal.data.local.preferences.PokemonPreferences
import net.azarquiel.pokemonappreal.domain.interactor.InteractorCallback
import net.azarquiel.pokemonappreal.domain.interactor.local.PokemonInteractor
import net.azarquiel.pokemonappreal.infraestructure.Navigator
import net.azarquiel.pokemonappreal.infraestructure.utils.getOptionSelected
import net.azarquiel.pokemonappreal.presentation.common.model.PokemonModel
import net.azarquiel.pokemonappreal.presentation.common.model.PokemonTypes
import net.azarquiel.pokemonappreal.presentation.pokemonmain.adapter.CustomAdapterPokemon

/**
 * Created by mrmar on 10/1/18.
 */
class PokemonMainViewmodel(val navigator: Navigator,val pokemonPreferences: PokemonPreferences,val pokemonInteractor: PokemonInteractor) :BaseObservable(){


    lateinit var adapter: CustomAdapterPokemon

    val typeSelected : ObservableInt = ObservableInt(-1)

    fun buildPokemonAdapter(context: Context){
        this.adapter = CustomAdapterPokemon(context, R.layout.rowpokemon, arrayListOf(),{
            navigator.startDetailActivity(it)
        })
    }

    private fun updateListWithType(typeSelected: Int) {
        when(typeSelected){
            0 -> this.adapter.filterTypes(PokemonTypes.FLYING)
            1 -> this.adapter.filterTypes(PokemonTypes.FIRE)
            2 -> this.adapter.refresh()
        }
    }

    fun onOptionChange(radioGroup: RadioGroup, id:Int) {
        typeSelected.set(radioGroup.getOptionSelected())
        updateListWithType(typeSelected.get())
    }

    fun loadItems() {
        pokemonInteractor.getAllPokemons(object :InteractorCallback<ArrayList<PokemonModel>>{
            override fun success(data: ArrayList<PokemonModel>?) {
                data?.let { adapter.set(it) }
            }

            override fun fail(t: Throwable) {
            }
        })
    }

    fun searchQuery(query: String?) {
        adapter.filter.filter(query)
    }

    fun filterFavourites() {
        adapter.filterByIds(pokemonPreferences.getAllFavourites().keys.toCollection(arrayListOf()))
        //Todo make this function
    }


}