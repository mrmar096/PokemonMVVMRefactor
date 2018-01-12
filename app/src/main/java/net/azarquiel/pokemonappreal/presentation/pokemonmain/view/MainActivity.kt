package net.azarquiel.pokemonappreal.presentation.pokemonmain.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.content.res.AppCompatResources
import android.view.Menu
import android.view.MenuItem
import net.azarquiel.pokemonappreal.R
import net.azarquiel.pokemonappreal.data.local.realm.repository.local.PokemonRepositoryImpl
import net.azarquiel.pokemonappreal.databinding.ActivityMainBinding
import net.azarquiel.pokemonappreal.domain.interactor.local.PokemonInteractorImpl
import net.azarquiel.pokemonappreal.presentation.common.view.activity.BaseActivitySearchable
import net.azarquiel.pokemonappreal.presentation.pokemonmain.viewmodel.PokemonMainViewmodel

class MainActivity : BaseActivitySearchable() {

    override fun getContentView(): Int = R.layout.activity_main

    private lateinit var viewmodel: PokemonMainViewmodel
    private var isFavouritePressed = false

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_fav -> {
                if (isFavouritePressed) { // sacar los de la BDs
                    item.icon = AppCompatResources.getDrawable(this,android.R.drawable.star_off)
                    viewmodel.loadItems()
                }
                else { // sacar los de Favoritos
                    item.icon = AppCompatResources.getDrawable(this,android.R.drawable.star_on)
                    viewmodel.filterFavourites()
                }
                isFavouritePressed = !isFavouritePressed
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
    }

    private lateinit var binding: ActivityMainBinding

    override fun bindData(contentView: Int) {
        binding  = DataBindingUtil.setContentView(this,contentView)
        viewmodel = PokemonMainViewmodel(navigator,pokemonPrefs,PokemonInteractorImpl(PokemonRepositoryImpl(realm)))
        binding.viewmodel = viewmodel
        viewmodel.buildPokemonAdapter(this)
        viewmodel.loadItems()

    }

    override fun onResume() {
        super.onResume()
        if(isFavouritePressed)viewmodel.filterFavourites()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        viewmodel.searchQuery(query)
        return false
    }


}
