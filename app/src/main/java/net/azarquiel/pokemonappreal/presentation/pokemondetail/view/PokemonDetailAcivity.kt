package net.azarquiel.pokemonappreal.presentation.pokemondetail.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import io.realm.Realm
import net.azarquiel.pokemonappreal.R

import kotlinx.android.synthetic.main.activity_pokemon_detail_acivity.*
import kotlinx.android.synthetic.main.content_pokemon_detail_acivity.*
import net.azarquiel.pokemonappreal.data.local.realm.model.AbilityRealm
import net.azarquiel.pokemonappreal.data.local.realm.model.PokemonAbilitiesRealm
import net.azarquiel.pokemonappreal.data.local.realm.model.PokemonTypesRealm
import net.azarquiel.pokemonappreal.data.local.realm.model.TypeRealm
import net.azarquiel.pokemonappreal.presentation.common.view.activity.BaseActivity
import net.azarquiel.pokemonappreal.presentation.pokemonmain.view.MainActivity

class PokemonDetailAcivity : BaseActivity() {
    override fun getContentView(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var realm: Realm

    private lateinit var favoritosSH: SharedPreferences

    private var pokemon_id: Long = 0
    private var name: String = ""
    private var flagFavorito: Boolean= true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail_acivity)
        setSupportActionBar(toolbar)
        favoritosSH = getSharedPreferences("favoritos", Context.MODE_PRIVATE)


        realm = Realm.getDefaultInstance()

        pokemon_id = intent.getLongExtra("pokemon_id",0L)
        name = intent.getStringExtra("name")

        val pokemonSH = favoritosSH.getString(pokemon_id.toString(),"nosta")
        flagFavorito = if (pokemonSH == "nosta"){
            fab.setImageResource(android.R.drawable.star_off)
            false
        }
        else{
            fab.setImageResource(android.R.drawable.star_on)
            true
        }
        val id = resources.getIdentifier("p"+pokemon_id,"drawable",packageName)
        ivpokemondetail.setImageResource(id)
        tvpokemondetail.text = name
        val types = realm.where(PokemonTypesRealm::class.java).equalTo("pokemon_id",pokemon_id).findAll()
        val abilities = realm.where(PokemonAbilitiesRealm::class.java).equalTo("pokemon_id",pokemon_id).findAll()
        var tv:TextView
        var typeTXT:String
        var lp= LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(10,10,10,10)

        for (types in types){
            tv = TextView(this)
            tv.setPadding(10,10,10,10)
            tv.layoutParams = lp
            tv.setBackgroundColor(Color.BLACK)
            tv.setTextColor(Color.WHITE)
            typeTXT = realm.where(TypeRealm::class.java).equalTo("type_id", types.type_id).findFirst().name
            tv.text = typeTXT
            lhtypesdetail.addView(tv)
        }
        var abilityTXT:String
        for (ability in abilities){
            tv = TextView(this)
            tv.setPadding(10,10,10,10)
            tv.layoutParams = lp
            abilityTXT = realm.where(AbilityRealm::class.java).equalTo("ability_id", ability.ability_id).findFirst().name
            tv.text = abilityTXT
            lhabilitiesdetail.addView(tv)
        }

        fab.setOnClickListener {
            favorito()
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK, Intent(this, MainActivity::class.java))
        super.onBackPressed()
    }

    private fun favorito() {
        val editor = favoritosSH.edit()
        if (flagFavorito) {
            editor.remove(pokemon_id.toString())
            fab.setImageResource(android.R.drawable.star_off)
            Toast.makeText(this,"Removed from favorites...", Toast.LENGTH_LONG).show()
        }
        else{
            editor.putString(pokemon_id.toString(), name)
            fab.setImageResource(android.R.drawable.star_on)
            Toast.makeText(this,"Added to favorites...", Toast.LENGTH_LONG).show()
        }
        flagFavorito = !flagFavorito
        editor.apply()
    }

}
