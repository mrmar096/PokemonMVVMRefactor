package net.azarquiel.pokemonappreal.infraestructure.binding

import android.databinding.BindingAdapter
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import net.azarquiel.pokemonappreal.presentation.pokemonmain.adapter.CustomAdapterPokemon

/**
 * Created by mrmar on 11/1/18.
 */
object CustomBindingAdapter {

    @JvmStatic
    @BindingAdapter("adapter")
    fun setAdapter(recyclerView: RecyclerView, adapter:CustomAdapterPokemon) {
        val mLayoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("pokemonDrawableId")
    fun setPokemonDrawableId(view: ImageView, id:Long) {
        if(id==-1L)return
        val identifier = view.resources.getIdentifier("p$id", "drawable", view.context.packageName)
        view.setImageResource(identifier)
    }
    @JvmStatic
    @BindingAdapter("addChildTextView")
    fun addChildTextView(linearLayout: LinearLayout,list: MutableList<String>) {
        linearLayout.removeAllViews()
        val lp= LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(10,10,10,10)
        list.forEach {
            val tv = TextView(linearLayout.context)
            tv.setPadding(10,10,10,10)
            tv.layoutParams = lp
            tv.setBackgroundColor(Color.BLACK)
            tv.setTextColor(Color.WHITE)
            tv.text = it
            linearLayout.addView(tv)
        }

    }
}