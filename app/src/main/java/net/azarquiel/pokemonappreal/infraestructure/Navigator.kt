package net.azarquiel.pokemonappreal.infraestructure

import android.content.Intent

/**
 * Created by mrmar on 10/1/18.
 */
interface Navigator {
    fun startDetailActivity(pokemon_id: Long)
    fun startActivity(intent: Intent)
}