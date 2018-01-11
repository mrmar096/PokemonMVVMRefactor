package net.azarquiel.pokemonappreal.data.local.preferences

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by mrmar on 11/1/18.
 */
class PokemonPreferencesImpl(val context: Context): PokemonPreferences {

    companion object {
        private val PREFS_NAME = "pokemon_prefs"
    }


    private val sharedPreferences : SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }



    override fun isFavourite(id: Long) =
            sharedPreferences.getString(id.toString(), null)!=null


    override fun getAllFavourites(): MutableMap<Long,String> =
            sharedPreferences.all.mapKeys { it.key.toLong()  }.mapValues { it.value.toString() }.toMutableMap()

    override fun saveFavourite(id: Long,name:String) {
            sharedPreferences.edit().putString(id.toString(),name).apply()
    }
}