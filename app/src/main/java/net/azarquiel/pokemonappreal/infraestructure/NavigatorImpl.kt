package net.azarquiel.pokemonappreal.infraestructure

import android.app.Activity
import android.content.Context
import android.content.Intent
import net.azarquiel.pokemonappreal.presentation.pokemondetail.view.PokemonDetailActivity

/**
 * Created by mrmar on 10/1/18.
 */
class NavigatorImpl(val activityContext: Context) : Navigator {

    override fun startDetailActivity(pokemon_id: Long) {
        val intent = Intent(activityContext as Activity, PokemonDetailActivity::class.java)
        intent.putExtra("pokemon_id", pokemon_id)
        startActivity(intent)
    }

    override fun startActivity(intent: Intent) {
        activityContext.startActivity(intent)
    }
}