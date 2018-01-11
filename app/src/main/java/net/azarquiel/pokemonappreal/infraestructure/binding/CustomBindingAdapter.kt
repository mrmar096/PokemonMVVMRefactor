package net.azarquiel.pokemonappreal.infraestructure.binding

import android.databinding.BindingAdapter
import android.widget.ImageView

/**
 * Created by mrmar on 11/1/18.
 */
object CustomBindingAdapter {

    @JvmStatic
    @BindingAdapter("pokemonDrawableId")
    fun setPokemonDrawableId(view: ImageView, id:Long) {
        if(id==-1L)return
        view.resources.getIdentifier("p$id","drawable",view.context.packageName)
    }
}