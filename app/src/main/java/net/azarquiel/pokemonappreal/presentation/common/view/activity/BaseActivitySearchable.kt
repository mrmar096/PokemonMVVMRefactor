package net.azarquiel.pokemonappreal.presentation.common.view.activity

import android.view.Menu
import android.widget.SearchView
import net.azarquiel.pokemonappreal.R

/**
 * Created by mrmar on 10/1/18.
 */
abstract class BaseActivitySearchable : BaseActivity(), SearchView.OnQueryTextListener {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Search..."
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

}