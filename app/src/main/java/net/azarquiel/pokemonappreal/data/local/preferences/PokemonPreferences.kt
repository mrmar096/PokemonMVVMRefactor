package net.azarquiel.pokemonappreal.data.local.preferences

/**
 * Created by mrmar on 11/1/18.
 */
interface PokemonPreferences {

    fun isFavourite(id:Long): Boolean
    fun getAllFavourites(): MutableMap<Long,String>
    fun saveFavourite(id: Long, name: String)
}