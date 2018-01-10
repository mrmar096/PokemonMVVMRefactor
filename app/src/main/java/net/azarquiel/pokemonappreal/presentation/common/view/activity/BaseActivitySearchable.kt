package net.azarquiel.pokemonappreal.presentation.common.view.activity

import android.view.Menu
import android.widget.SearchView
import net.azarquiel.pokemonappreal.R

/**
 * Created by mrmar on 10/1/18.
 */
abstract class BaseActivitySearchable : BaseActivity(), SearchView.OnQueryTextListener {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Search..."
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}