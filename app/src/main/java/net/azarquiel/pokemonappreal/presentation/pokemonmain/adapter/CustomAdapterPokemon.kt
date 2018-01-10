package net.azarquiel.pokemonappreal.presentation.pokemonmain.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import io.realm.Realm
import kotlinx.android.synthetic.main.rowpokemon.view.*
import net.azarquiel.pokemonappreal.data.local.realm.model.PokemonRealm
import net.azarquiel.pokemonappreal.data.local.realm.model.PokemonTypesRealm
import net.azarquiel.pokemonappreal.data.local.realm.model.TypeRealm
import net.azarquiel.pokemonappreal.presentation.pokemondetail.view.PokemonDetailAcivity

/**
 * Created by pacopulido on 02/11/17.
 */
class CustomAdapterPokemon(val context: Context,
                           val layout: Int,
                           val dataList: ArrayList<PokemonRealm>
                    ): RecyclerView.Adapter<CustomAdapterPokemon.ViewHolder>() {
    companion object {
        private val REQUEST_DETALLE=0
    }

    private var realm: Realm = Realm.getDefaultInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item,position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {


        fun bind(dataItem: PokemonRealm, position: Int){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val id = context.resources.getIdentifier("p"+dataItem.pokemon_id,"drawable",context.packageName)
            itemView.ivpokemonrow.setImageResource(id)
            itemView.tvpokemonrow.text = dataItem.name
            val types = realm.where(PokemonTypesRealm::class.java).equalTo("pokemon_id",dataItem.pokemon_id).findAll()
            var tv:TextView
            var typeTXT:String
            var lp= LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            lp.setMargins(10,10,10,10)
            itemView.lineartypes.removeAllViews()
            for (types in types){
                tv = TextView(context)
                tv.setPadding(10,10,10,10)
                tv.layoutParams = lp
                tv.setBackgroundColor(Color.BLACK)
                tv.setTextColor(Color.WHITE)
                typeTXT = realm.where(TypeRealm::class.java).equalTo("type_id", types.type_id).findFirst().name
                tv.text = typeTXT
                itemView.lineartypes.addView(tv)
            }
            itemView.setOnClickListener({
                onItemClick(dataItem)
            })
        }

    }

    private fun onItemClick(dataItem: PokemonRealm) {
        val intent = Intent(context as Activity, PokemonDetailAcivity::class.java)
        intent.putExtra("pokemon_id", dataItem.pokemon_id)
        intent.putExtra("name", dataItem.name)
        context.startActivityForResult(intent, REQUEST_DETALLE)
    }

}