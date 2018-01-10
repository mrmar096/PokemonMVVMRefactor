package net.azarquiel.pokemonappreal.presentation.pokemonmain.view

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.support.v7.content.res.AppCompatResources
import android.view.MenuItem
import android.widget.SearchView
import io.realm.Realm
import io.realm.RealmResults
import net.azarquiel.pokemonappreal.R
import net.azarquiel.pokemonappreal.presentation.pokemonmain.adapter.CustomAdapterPokemon
import net.azarquiel.pokemonappreal.data.local.realm.model.PokemonRealm
import net.azarquiel.pokemonappreal.databinding.ActivityMainBinding
import net.azarquiel.pokemonappreal.presentation.common.view.activity.BaseActivitySearchable
import net.azarquiel.pokemonappreal.presentation.pokemonmain.viewmodel.PokemonMainViewmodel

// ************* Filtro ************ , SearchView.OnQueryTextListener
class MainActivity : BaseActivitySearchable() {
    override fun getContentView(): Int = R.layout.activity_main

    override fun getMenu(): Int  = R.menu.menu_main

    companion object {

        private val REQUEST_DETALLE=0
    }
    private lateinit var realm: Realm
    private lateinit var pokemons: RealmResults<PokemonRealm>
    private lateinit var adapter: CustomAdapterPokemon
    private lateinit var favoritosSH: SharedPreferences
    private lateinit var pokemonsAL: ArrayList<PokemonRealm>
    // ************* Filtro ************
    private lateinit var searchView: SearchView
    private lateinit var original: ArrayList<PokemonRealm>
    // ************* Filtro ************
    private var flagFavoritos = false
    /* TODO CHANGE THIS
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            setSupportActionBar(toolbar)
            // Get Data
            favoritosSH = getSharedPreferences("favoritos", Context.MODE_PRIVATE)
            realm = Realm.getDefaultInstance() // singleton
            // datos de BD que no cambian a lo largo de la app
            pokemons = realm.where(PokemonRealm::class.java).findAll()
            // Instanciamos los datas
            pokemonsAL = ArrayList<PokemonRealm>()
            // ************* Filtro ************
            original = ArrayList<PokemonRealm>()
            // ************* / Filtro ************
            // rv con adapter (datalist vacio, pero lo adapter para notifys posteriores)
            rvpokemon.layoutManager = LinearLayoutManager(this)
            adapter = CustomAdapterPokemon(this, R.layout.rowpokemon,pokemonsAL)
            rvpokemon.adapter = adapter
            // Inicialmente visualizamos los de la BDs
            getDB()
        }

    */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_fav -> {
                if (flagFavoritos) { // sacar los de la BDs
                    item.icon = AppCompatResources.getDrawable(this,android.R.drawable.star_off)
                    getDB()
                }
                else { // sacar los de Favoritos
                    item.icon = AppCompatResources.getDrawable(this,android.R.drawable.star_on)
                    getFavoritos()
                }
                flagFavoritos = !flagFavoritos
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getDB() {
        pokemonsAL.clear()
        for (pokemon in pokemons){
            pokemonsAL.add(pokemon)
        }
        refresh()
    }

    override fun bindData(contentView: Int) {
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,contentView)
        binding.viewmodel = PokemonMainViewmodel()
    }

    private fun getFavoritos() {
        val favoritos = favoritosSH.all
        pokemonsAL.clear()
        for ((entry,value) in favoritos.entries){
            pokemonsAL.add(PokemonRealm(entry.toLong(), value.toString()))
        }
        refresh()
    }

    private fun refresh() {
        // ************* Filtro ************
        original.clear()
        original.addAll(pokemonsAL)
        // ************* / Filtro ************
        adapter.notifyDataSetChanged()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == REQUEST_DETALLE){
                if (flagFavoritos){
                    getFavoritos()
                }
            }
        }
    }

    // ************* Filtro ************
    /* TODO MOVE THIS
    override fun onQueryTextChange(query: String): Boolean {
        //Toast.makeText(this, newText, Toast.LENGTH_SHORT).show()
        val filteredList = ArrayList<PokemonRealm>()

        // Partimos del data original antes de filtrar
        pokemonsAL.clear()
        pokemonsAL.addAll(original)

        // Filtramos

        if (query.isEmpty()){
            filteredList.addAll(pokemonsAL)
        }
        else {
            pokemonsAL.filterTo(filteredList) { it.name.startsWith(query,true) }
//            for (pokemon in pokemonsAL) {
//                if (pokemon.name.startsWith(query,true)) {
//                    filteredList.add(pokemon)
//                }
//            }
        }

        // ponemos el nuevo data en la referencia del data del adaptador y notify
        pokemonsAL.clear()
        pokemonsAL.addAll(filteredList)
        adapter.notifyDataSetChanged()

        return false
    }


    // ************* / Filtro ************
    private fun tostada(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }
*/
}
